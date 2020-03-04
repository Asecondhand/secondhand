package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.Graph;
import com.secondhand.module.sys.mapper.GraphMapper;
import com.secondhand.module.sys.service.GraphService;
@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService{

    @Override
    public boolean follow(Graph graph) {
        return this.save(graph);
    }

    @Override
    public List<Graph> followList(Long followId) {
        LambdaQueryWrapper<Graph> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Graph::getFollowid,followId);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<Graph> uList(Long uid) {
        LambdaQueryWrapper<Graph> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Graph::getUid,uid);
        return this.list(lambdaQueryWrapper);
    }
}
