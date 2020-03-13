package com.secondhand.util.fileUtil;
import com.secondhand.module.product.controller.ProductController;
import com.secondhand.util.exception.RRException;
import com.secondhand.util.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileService {
    @Value("${resources.path}")
    private String rootPath;

    @Value("${resources.productsPath}")
    private String productsPath;
    //request 不应该放任何配置路径的信息
    // 面向对象
    public String uploadFile(FileRequest request) throws Exception {

        /**
         * 获取文件大小、类型
         */
            MultipartFile file = request.getFile();
        String fileName = file.getOriginalFilename();
        String fileNamePrifix = fileName.substring(fileName.lastIndexOf("."));
        long fileSize = file.getSize();
        /**
         * 【校验判断】大小、类型
         */
        if (file.isEmpty()) {
            throw new RRException("上传文件失败！没有选择文件，或上传的文件为空！");
        }
        if (!request.getTypeConstraint().contains(fileNamePrifix)) {
            throw new RRException("上传文件失败！不支持上传" + fileName + "类型的文件！");
        }
        if (request.getSizeConstraint() < fileSize) {
            throw new RRException("上传文件失败！尝试上传的文件大小超出了限制！仅允许上传不超过" + (request.getSizeConstraint()/1024) + "KB的文件！");
        }
        /**
         * 【建立目录、确认目录】
         * relativeStoragePath：相对存储路径，/imgages/products/日期/文件名
         * absoluteStoragePath：绝对存储路径，带有当前环境的resoulce作为前缀
         */
        //逻辑实在太零散了，一个rootPath在sevice下，一个request配置在request里面
        //没有为后续获得路径做考虑
        String relativeStoragePath =  "/"+request.getStoragePath();
        String absoluteStoragePath = rootPath + relativeStoragePath;
        File dir = new File(absoluteStoragePath);
        if (!dir.exists()) dir.mkdirs();//如果文件路径不存在,则创建文件夹
        /**
         * 【生成文件名并上传】
         */
        fileName = IDHelper.createSnowFlateWorker() + fileNamePrifix;
        file.transferTo(new File(absoluteStoragePath + "/" + fileName));
        return fileName;
    }

    public Resource loadFileByFilename(String filename){
        Path absoluteStoragePath = Paths.get(rootPath + "/"+productsPath+"/"+filename);
        try {
            Resource resource =new UrlResource(absoluteStoragePath.toUri());
            if(resource.exists() && resource.isReadable())
                return resource;
        } catch (MalformedURLException e) {
            throw  new ServiceException(e.getMessage());
        }
        throw new ServiceException("文件不存在或者不可读");
    }

}
