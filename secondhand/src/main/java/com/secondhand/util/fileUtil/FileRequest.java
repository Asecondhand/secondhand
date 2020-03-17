package com.secondhand.util.fileUtil;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    private String typeConstraint = ".jpg,.png,.gif";
    private Integer sizeConstraint = 2 * 1024 * 1024;
    private String storagePath;
    private MultipartFile[] file;


    public FileRequest(MultipartFile[] file, String storagePath, String typeConstraint, Integer sizeConstraint) {
        this.typeConstraint = typeConstraint;
        this.sizeConstraint = sizeConstraint;
        this.storagePath = storagePath;
        this.file = file;
    }


    public FileRequest(MultipartFile[] file, String storagePath) {
        this.file = file;
        this.storagePath = storagePath;
    }
}
