package com.secondhand.module.mime.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.constant.Constant;
import com.secondhand.module.mime.service.IUserPraiseService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户点赞的商品关联表 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/api/userPraise")
public class UserPraiseController {
    @Autowired
    private IUserPraiseService iUserPraiseService;

    @GetMapping
    public ApiResult getUserPraiseByUserId(Page page) {
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(iUserPraiseService.getUserPraiseByUserId(userId, page));
    }


    /**
     * 用户点赞
     * 取消点赞
     */
    @GetMapping("/{productId}")
    public ApiResult userPraise(@PathVariable("productId") Long productId) {
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(iUserPraiseService.userPraise(userId, productId));
    }

    // /**
    //  * 用户点赞
    //  */
    // @GetMapping("/createPraise/{productId}")
    // public ApiResult createPraise(@PathVariable("productId")Long productId){
    //     Long userId = ShiroUtils.getUserId();
    //     return ApiResult.success(iUserPraiseService.createPraise(userId,productId));
    // }

    // /**
    //  * 取消点赞
    //  */
    // @GetMapping("/deletePraise/{productId}")
    // public ApiResult deletePraise(@PathVariable("productId")Long productId){
    //     Long userId = ShiroUtils.getUserId();
    //     return ApiResult.success(iUserPraiseService.deletePraise(userId,productId));
    // }

}
