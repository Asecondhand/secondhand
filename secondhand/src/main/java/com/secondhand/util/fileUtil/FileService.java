package com.secondhand.util.fileUtil;
import com.secondhand.util.exception.RRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileService {
    @Value("${resources.path}")
    private String rootPath;

    public
    String uploadFile(FileRequest request) throws Exception {

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
        if (request.getTypeConstraint().indexOf(fileNamePrifix) == -1) {
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
        String dateDirName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String relativeStoragePath =  "/"+request.getStoragePath() + dateDirName;
        String absoluteStoragePath = rootPath + relativeStoragePath;
        File dir = new File(absoluteStoragePath);
        if (!dir.exists()) dir.mkdirs();//如果文件路径不存在,则创建文件夹
        /**
         * 【生成文件名并上传】
         */
        fileName = IDHelper.createSnowFlateWorker() + fileNamePrifix;
        file.transferTo(new File(absoluteStoragePath + "/" + fileName));
        return relativeStoragePath.substring(1, relativeStoragePath.length()) + "/" + fileName;
    }
}
