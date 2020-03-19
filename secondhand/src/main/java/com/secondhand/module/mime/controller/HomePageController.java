package com.secondhand.module.mime.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erica
 * @since 2020/3/19
 */
@RestController
@RequestMapping("/api/homePage")
public class HomePageController {
    @Autowired
    ProductService productService;

    /**
     * 个人主页
     * 我的
     */
    @GetMapping("/mine")
    public ApiResult mineProductByUserId(){
        Long userId = ShiroUtils.getUserId();
        return productService.mineProductByUserId(userId);
    }
}
