package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //todo graph需要更新状态
    @PostMapping
    public ApiResult<Boolean> followUser(@RequestBody Graph graph){
        return ApiResult.success(graphService.follow(graph));
    }

    //关注列表
    @GetMapping("/{uid}/followList")
    public ApiResult getFollowList(@PathVariable Long uid){
        return ApiResult.success(graphService.followList(uid));
    }
    //粉丝列表
    @GetMapping("/{uid}/userList")
    public ApiResult getUserList(@PathVariable Long uid){
        return ApiResult.success(graphService.uList(uid));
    }

//    /**
//     * 更改关注的状态
//     */
//    @PutMapping("/{id}/status")
//    public ApiResult updateStatus(@PathVariable Long id, @RequestBody GraphStatusDTO graphStatusDTO){
//        return ApiResult.success(graphService.updateStatus(id, graphStatusDTO));
//    }
}
