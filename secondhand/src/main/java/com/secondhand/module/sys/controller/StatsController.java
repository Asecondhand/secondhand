package com.secondhand.module.sys.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.ao.TimeAo;
import com.secondhand.module.sys.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Erica
 * @since 2020/4/19
 * 数据统计
 */
@RestController
@RequestMapping("/api/data")
public class StatsController {

    @Autowired
    private INoticeService iNoticeService;

    // 用户登录/购物数/
    @GetMapping("/site")
    public ApiResult getSiteList() {
        return iNoticeService.getSiteList();
    }

    // 详情
    @GetMapping("/productDetail")
    public ApiResult getProductList() {
        return iNoticeService.getProductList();
    }

    // 时间筛选标签
    @PostMapping("/productTag")
    public ApiResult getTagList(@RequestBody  TimeAo ao) {
        return iNoticeService.getTagList(ao);
    }

    //交易成交数量

}
