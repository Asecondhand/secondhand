package com.secondhand.module.sys.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.ao.LoginAo;
import com.secondhand.redis.RedisTool;
import com.secondhand.shiro.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Erica
 * @since 2020/3/7
 */
@RestController
public class LoginController {

    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private RedisTool redisTool;


    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody LoginAo po/*, String captcha, String uuid*/) {
        if (StringUtils.isEmpty(po.getUsername()) || StringUtils.isEmpty(po.getPassword())) {
            return ApiResult.fail("参数格式不正确");
        }
        String token = jwtTool.doLogin(po.getUsername(), po.getPassword(), "").getMessage();
        int code = jwtTool.doLogin(po.getUsername(), po.getPassword(), "").getCode();
        return ApiResult.success(code,"成功",token);
    }


}
