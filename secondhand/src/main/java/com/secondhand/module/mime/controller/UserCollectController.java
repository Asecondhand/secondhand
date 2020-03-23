package com.secondhand.module.mime.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.CollectAO;
import com.secondhand.module.mime.service.IUserCollectService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @return
     */
    @GetMapping
    public ApiResult getUserBuyByUserId() {
        Long userId = ShiroUtils.getUserId();
        return iUserCollectService.getUserCollectByUserId(userId);
    }

    /**
     * 用户收藏
     * 取消收藏
     */
    @PostMapping("/collectStatus")
    public ApiResult UpdateUserCollect(@RequestBody CollectAO ao){
        Long userId = ShiroUtils.getUserId();
        return iUserCollectService.UpdateUserCollectStatus(userId,ao);
    }

}
