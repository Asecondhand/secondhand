package com.secondhand.module.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.product.entity.Graph;

import java.util.List;

public interface GraphService extends IService<Graph>{

    /**
     * 关系
     */
    boolean follow(Graph graph);

    /**
     * 获得关注人列表
     */
    List<Graph> followList(Long followId);

    /**
     * 被关注列表
     */
    List<Graph> uList(Long uid);
}
