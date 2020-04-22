package com.secondhand.module.order.serivce.impl;

import com.secondhand.module.order.dto.OrderDTO;
import com.secondhand.module.order.dto.UpdateOrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.order.mapper.OrderMapper;
import com.secondhand.module.order.entity.Order;
import com.secondhand.module.order.serivce.OrderService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author zangjan
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Boolean createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        //初始化订单状态和版本
        order.setStatus(0);
        order.setVersion(0);
        return this.save(order);
    }

    //RC

    /**
     * 更新订单
     *
     * @param updateOrderDTO
     * @return Boolean
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public Boolean updateOrder(UpdateOrderDTO updateOrderDTO) {
        return this.baseMapper.updateOrder(updateOrderDTO);
    }
}

