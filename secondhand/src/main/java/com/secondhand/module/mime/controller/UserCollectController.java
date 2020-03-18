package com.secondhand.module.mime.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.service.IUserCollectService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户收藏的商品关联表 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/api/userCollect")
public class UserCollectController {

    @Autowired
    private IUserCollectService iUserCollectService;
    /**
     * "我收藏的"
     * 查询用户收藏的商品
     * @param page
     * @return
     */
    @GetMapping
    public ApiResult getuserBuyByUserId(Page page) {
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(iUserCollectService.getUserCollectByUserId(userId,page));
    }

    /**
     * 用户收藏
     * 取消收藏
     */
    @GetMapping("/{productId}")
    public ApiResult userCollect(@PathVariable("productId")Long productId){
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(iUserCollectService.userCollect(userId,productId));
    }

    // /**
    //  * 取消收藏
    //  */
    // @GetMapping("/deleteCollect/{productId}")
    // public ApiResult deleteCollect(@PathVariable("productId")Long productId){
    //     Long userId = ShiroUtils.getUserId();
    //     return ApiResult.success(iUserCollectService.deleteCollect(userId,productId));
    // }
}
