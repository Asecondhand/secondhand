package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-03 14:13
 **/
@RestController
@RequestMapping("/api/Graph")
public class GraphController {

    @Autowired
    GraphService graphService;
    @PostMapping
    public ApiResult<Boolean> followUser(@RequestBody Graph graph){
        return ApiResult.success(graphService.follow(graph));
    }

}
