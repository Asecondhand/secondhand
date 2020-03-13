package com.secondhand.module.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.entity.UserAttr;

import java.util.List;

public interface GraphService extends IService<Graph>{

    /**
     * 关系
     */
    boolean follow(Graph graph);

    /**
     * 获得关注列表
     */
    List<UserAttr> followList(Long followId);

    /**
     * 被关注列表
     */
    List<UserAttr> uList(Long uid);
}
