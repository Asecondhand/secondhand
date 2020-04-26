package com.secondhand.module.order.serivce.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.order.mapper.OrderProductMapper;
import com.secondhand.module.order.entity.OrderProduct;
import com.secondhand.module.order.serivce.OrderProductService;
/**
 * @author zangjan
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService{

}
