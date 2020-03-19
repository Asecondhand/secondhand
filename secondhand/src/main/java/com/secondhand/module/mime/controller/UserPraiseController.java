package com.secondhand.module.mime.controller;



import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.PriseAO;
import com.secondhand.module.mime.service.IUserPraiseService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult getUserPraiseByUserId() {
        Long userId = ShiroUtils.getUserId();
        return iUserPraiseService.getUserPraiseByUserId(userId);
    }


    /**
     * 用户点赞
     * 取消点赞
     */
    @PostMapping("/PriseStatus")
    public ApiResult UpdateUserPraise(@RequestBody PriseAO ao) {
        return iUserPraiseService.UpdateUserPraiseStatus(ao);
    }


}
