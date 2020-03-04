package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.service.LeaveMessageService;
import com.secondhand.module.product.vo.LeaveMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand3
 * @description:留言控制器
 * @author: zangjan
 * @create: 2020-02-27 17:01
 **/
@RestController
@RequestMapping("/api/leaveMessage")
public class LeaveMessageController {

    @Autowired
    LeaveMessageService leaveMessageService;
    /**
     * 上传评论
     */
    @PostMapping
    public ApiResult<Boolean> LeaveMessage(@RequestBody LeaveMessageVo leaveMessage){
        return ApiResult.success(leaveMessageService.upload(leaveMessage));
    }
}
