package com.secondhand.module.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.product.DTO.GraphStatusDTO;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.vo.FollowUserVo;

import java.util.List;

public interface GraphService extends IService<Graph>{

    /**
     * 关系
     */
    boolean follow(Graph graph);

    /**
     * 获得关注列表
     */
    List<FollowUserVo> followList(Long followId);

    /**
     * 被关注列表
     */
    List<FollowUserVo> uList(Long uid);

    boolean updateStatus(Long id , GraphStatusDTO graphStatusDTO);
}
