package com.secondhand.module.sys.service;

import com.secondhand.module.sys.entity.Graph;
import com.baomidou.mybatisplus.extension.service.IService;

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
