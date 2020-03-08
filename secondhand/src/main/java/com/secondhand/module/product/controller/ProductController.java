package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.util.fileUtil.FileRequest;
import com.secondhand.util.fileUtil.FileService;
import com.secondhand.util.fileUtil.IDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: secondhand3
 * @description: 商品控制器
 * @author: zangjan
 * @create: 2020-02-25 10:16
 **/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${resources.productsPath}")
    private String productsPath;
    @Value("${resources.path}")
    private String rootPath;

    /**
     * 商品发布
     */
    @PostMapping("/issue")
    public ApiResult<Boolean> issueProduct(@RequestBody Product product) {
        return ApiResult.success(productService.issue(product));
    }

    /**
     * 查询商品详情 需要把留言区的评论一起返回
     * 不需要登录
     *
     * @return
     */
    @GetMapping("/search/{id}")
    public ApiResult<Product> searchById(@PathVariable String id) {
        return ApiResult.success(productService.search(id));
    }

    /**
     * 修改商品的上下架
     */

    @PostMapping("/status")
    public ApiResult<Boolean> changeStatus(@RequestParam("status") int status, @RequestParam("productId") Long productId) {
        return ApiResult.success(productService.changeStatus(status, productId));
    }


    @PostMapping("/upLoadImage")
    public ApiResult upLoadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return ApiResult.success("成功", fileService.uploadFile(
                new FileRequest(file, productsPath)
        ));
    }
}
