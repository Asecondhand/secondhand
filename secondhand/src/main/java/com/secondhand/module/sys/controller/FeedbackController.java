package com.secondhand.module.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.FeedAo;
import com.secondhand.module.sys.ao.FeedbackAo;
import com.secondhand.module.sys.entity.Feedback;
import com.secondhand.module.sys.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService iFeedbackService;

    //前台添加反馈
    @PostMapping("/add")
    public ApiResult addFeedback(@RequestBody @Validated  FeedAo ao) {
        return iFeedbackService.addFeedback(ao);
    }

    // 更新反馈消息状态接口 已读 list集合
    @PostMapping("/update/status")
    public ApiResult updateFeedback(@RequestBody FeedbackAo ao) {
        Integer status = 1;
        return iFeedbackService.updateFeedback(ao, status);
    }

    //删除已读/已处理消息 list
    @PostMapping("/del")
    public ApiResult delFeedback(@RequestBody FeedbackAo ao) {
        return iFeedbackService.delFeedback(ao);
    }

    // 待办事项列表 已读
    @GetMapping("/list/read")
    public PageApiResult listFeedbackType1(Integer pageIndex, Integer pageSize) {
        Page page = new Page<>(pageIndex, pageSize);
        Integer type = 1;
        return iFeedbackService.listFeedback(page, type);
    }


    // 待办事项列表 未处理
    @GetMapping("/list/untreated")
    public PageApiResult listFeedbackType2(Integer pageIndex, Integer pageSize){
        Page page = new Page<>(pageIndex, pageSize);
        Integer type = 2;
        return iFeedbackService.listFeedback(page,type);
    }

    // 待办事项列表 已处理
    @GetMapping("/list/deal")
    public PageApiResult listFeedbackType0(Integer pageIndex, Integer pageSize){
        Page page = new Page<>(pageIndex, pageSize);
        Integer type = 0;
        return iFeedbackService.listFeedback(page,type);
    }

}
