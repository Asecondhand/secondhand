package com.secondhand.module.order.serivce;

import com.secondhand.module.order.dto.OrderDTO;
import com.secondhand.module.order.dto.UpdateOrderDTO;
import com.secondhand.module.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zangjan
 */
public interface OrderService extends IService<Order> {


    /**
     * 创建订单
     *
     * @param orderDTO
     * @return Boolean
     */
    Boolean createOrder(OrderDTO orderDTO);

    /**
     * 更新订单
     */
    Boolean updateOrder(UpdateOrderDTO updateOrderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    Boolean payOrder(OrderDTO orderDTO);
}





