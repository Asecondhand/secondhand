package com.secondhand.module.home.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.service.EsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand
 * @description:搜索关键词提示
 * @author: zangjan
 * @create: 2020-04-04 20:07
 **/
@RequestMapping("/api/keyword")
@RestController
public class KeywordTipController {

    @Autowired
    EsSearchService esSearchService;
    /**
     * 关键词提示功能
     * @param keyword
     * @return
     */
    @GetMapping
    public ApiResult tips(@RequestParam("keyword") String keyword){
        return ApiResult.success(esSearchService.getTips(keyword));
    }
}
