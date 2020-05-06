package com.secondhand.module.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.entity.Notice;
import com.secondhand.module.sys.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;

    // 添加公告
    @PostMapping("/add")
    public ApiResult addNoticeList(@RequestBody Notice ao) {
        return iNoticeService.addNoticeList(ao);
    }

    //展示公告
    @GetMapping("/list")
    public PageApiResult getNoticeList(String keyWord,Integer pageIndex, Integer pageSize) {
        Page page = new Page<>(pageIndex, pageSize);
        return iNoticeService.getNoticeList(page,keyWord);
    }

    @GetMapping("/detail/{noticeId}")
    public ApiResult getNoticeDetail(@PathVariable("noticeId") Long noticeId) {
        return iNoticeService.getNoticeDetail(noticeId);
    }

    @GetMapping("/update/{noticeId}")
    public ApiResult updateNoticeType(@PathVariable("noticeId") Long noticeId) {
        return iNoticeService.updateNoticeType(noticeId);
    }


    //展示公告
    @GetMapping("/query")
    public ApiResult getNotice() {
        return iNoticeService.getNotice();
    }


}
