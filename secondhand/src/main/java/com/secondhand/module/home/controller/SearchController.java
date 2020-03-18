package com.secondhand.module.home.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.service.EsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand3
 * @description:搜索用户，鱼塘等
 * @author: zangjan
 * @create: 2020-03-17 12:17
 **/
@RequestMapping("/api/search")
@RestController
public class SearchController {

    @Autowired
    EsSearchService esSearchService;
    /**
     * 查找物品和用户
     */
    @GetMapping
    public ApiResult SearchUserOrProductByKeyword(@RequestParam("keyword")String keyword){
        return ApiResult.success(esSearchService.SearchUserOrProductByKeyword(keyword));
    }
}
