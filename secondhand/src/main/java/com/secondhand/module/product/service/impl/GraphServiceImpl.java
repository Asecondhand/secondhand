package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.mapper.GraphMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements IService<Graph> {

}
