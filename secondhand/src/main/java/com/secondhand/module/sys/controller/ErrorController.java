package com.secondhand.module.sys.controller;

import com.secondhand.common.basemethod.ApiResult;
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
    public ApiResult<String> tokenExpired(){
        return ApiResult.fail(401,"认证失败，token已过期");
    }
}
