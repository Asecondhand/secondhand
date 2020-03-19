package com.secondhand.module.mime.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.service.IUserSaleService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户卖出的商品关联表 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/api/userSale")
public class UserSaleController {
    @Autowired
    private IUserSaleService iUserSaleService;


    /**
     * "我卖出的"
     * 查询用户卖出的商品
     * @return
     */
    @GetMapping
    public ApiResult getUserSaleByUserId() {
        Long userId = ShiroUtils.getUserId();
        return iUserSaleService.getUserSaleByUserId(userId);
    }


}
