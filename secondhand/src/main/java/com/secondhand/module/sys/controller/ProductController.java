package com.secondhand.module.sys.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Product;
import com.secondhand.module.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 商品发布
     */
    @PostMapping("/issue")
    public ApiResult<Boolean>  issueProduct(@RequestBody Product product){
        return ApiResult.success(productService.issue(product));
    }

    /**
     * 查询商品详情 需要把留言区的评论一起返回
     * 不需要登录
     * @return
     */
    @GetMapping("/search/{id}")
    public ApiResult<Product> searchById(@PathVariable String id){
        return ApiResult.success(productService.search(id));
    }

    /**
     * 修改商品的上下架
     */

    @PostMapping("/status")
    public ApiResult<Boolean> changeStatus(@RequestParam("status") int status,@RequestParam("productId") Long productId){
        return ApiResult.success(productService.changeStatus(status,productId));
    }
}
