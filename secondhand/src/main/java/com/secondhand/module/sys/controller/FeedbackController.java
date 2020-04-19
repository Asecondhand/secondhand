package com.secondhand.module.sys.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Feedback;
import com.secondhand.module.sys.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/api/sysFeedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService iFeedbackService;

    //前台添加反馈
    // @PostMapping("/add")
    // public ApiResult addFeedback(@RequestBody Feedback ao){
    //
    // }
    // 后台反馈列表

    // 更新反馈消息状态接口

}
