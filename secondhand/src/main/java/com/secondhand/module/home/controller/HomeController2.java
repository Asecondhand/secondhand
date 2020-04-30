package com.secondhand.module.home.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-30 13:05
 **/
@RestController
@RequestMapping("/home")
public class HomeController2 {

    @Autowired
    HomeService homeService;
    @GetMapping
    public ApiResult  getHome(){
        return ApiResult.success(homeService.getHome());
    }
}
