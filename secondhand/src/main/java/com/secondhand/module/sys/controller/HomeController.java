package com.secondhand.module.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.FeedbackAo;
import com.secondhand.module.sys.service.IFeedbackService;
import com.secondhand.module.sys.vo.AfterUserInfoVo;
import com.secondhand.module.sys.vo.CurrentUserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Erica
 * @since 2020/4/19
 */
@RestController
@RequestMapping("/api/sysHome")
public class HomeController {
    @Autowired
    private IFeedbackService iFeedbackService;


    // 更新反馈消息状态接口 已处理 list集合
    @PostMapping("/update/status")
    public ApiResult updateFeedback(@RequestBody FeedbackAo ao){
        Integer status = 0;
        return iFeedbackService.updateFeedback(ao,status);
    }


    // 后台首页反馈列表
    @GetMapping("/list")
    public PageApiResult listFeedback(Integer pageIndex, Integer pageSize){
        Page page = new Page<>(pageIndex, pageSize);
        return iFeedbackService.getFeedback(page);
    }

    //个人信息
    @GetMapping("/get/userInfo")
    public ApiResult getUserInfoAfter(){
        CurrentUserVo currentUserVo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        AfterUserInfoVo vo = new AfterUserInfoVo();
        BeanUtils.copyProperties(currentUserVo, vo);
        return ApiResult.success(vo);
    }

}
