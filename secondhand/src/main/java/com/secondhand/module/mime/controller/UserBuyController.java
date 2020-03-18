package com.secondhand.module.mime.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.service.IUserBuyService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户购买到的商品关联表 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/api/userBuy")
public class UserBuyController {

    @Autowired
    private IUserBuyService iUserBuyService;

    /**
     * "我买到的"
     * 查询用户买到的商品
     * @param page
     * @return
     */
    @GetMapping
    public ApiResult getUserBuyByUserId( Page page) {
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(iUserBuyService.getUserBuyByUserId(userId,page));
    }

}
