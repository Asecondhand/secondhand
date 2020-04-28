package com.secondhand.module.mime.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.sys.service.UserAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    UserAttrService userAttrService;

    /**
     * 个人主页
     * 我的
     */
    @GetMapping("/mine/{uid}")
    public ApiResult mineProductByUserId(@PathVariable("uid")Long uid){
        return productService.mineProductByUserId(uid);
    }

    /**
     * 动态
     */
    @GetMapping("/dynamic/{uid}")
    public ApiResult personalDynamic(@PathVariable("uid")Long uid){
        return productService.personalDynamic(uid);
    }

    /**
     * 个人主页
     * 个人信息
     */
    @GetMapping("/userInfo/{uid}")
    public ApiResult getUserInfoByUserId(@PathVariable("uid")Long uid){
        return userAttrService.getUserInfoByUserId(uid);
    }

}
