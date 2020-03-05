package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.mapper.GraphMapper;
import com.secondhand.module.product.service.GraphService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService {

    @Override
    public boolean follow(Graph graph) {
        return false;
    }

    @Override
    public List<Graph> followList(Long followId) {
        return null;
    }

    @Override
    public List<Graph> uList(Long uid) {
        return null;
    }
}
