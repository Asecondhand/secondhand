package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{followId}/followList")
    public ApiResult<List<UserAttr>> getFollowList(@PathVariable Long followId){
        return ApiResult.success(graphService.followList(followId));
    }
    @GetMapping("/{uid}/userList")
    public ApiResult<List<UserAttr>> getUserList(@PathVariable Long uid){
        return ApiResult.success(graphService.uList(uid));
    }
}
