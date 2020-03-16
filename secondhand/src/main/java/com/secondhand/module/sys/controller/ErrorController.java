package com.secondhand.module.sys.controller;

import com.secondhand.common.basemethod.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-02-28 10:09
 **/
@RestController
public class ErrorController {

    @RequestMapping("/tokenExpired")
    public ResponseEntity<ApiResult<String>> tokenExpired(){
        return new ResponseEntity<ApiResult<String>>(ApiResult.fail("认证失败，token已过期"), HttpStatus.valueOf(401));
    }
}
