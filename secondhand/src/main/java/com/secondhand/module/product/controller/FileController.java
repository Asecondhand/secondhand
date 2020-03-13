package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.util.fileUtil.FileRequest;
import com.secondhand.util.fileUtil.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-10 03:07
 **/
@RestController
@RequestMapping("/fileSystem")
public class FileController {
    @Autowired
    FileService fileService;

    @Value("${resources.productsPath}")
    private String productsPath;

    @PostMapping("/upLoadImage")
    public ApiResult upLoadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return ApiResult.success("成功", MvcUriComponentsBuilder.fromMethodName(FileController.class,"serachFile" , fileService.uploadFile(new FileRequest(file, productsPath)))
                .build().toUri().toString());
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serachFile(@PathVariable String filename){
        Resource file = fileService.loadFileByFilename(filename);
        return  ResponseEntity.ok().body(file);
    }
}
