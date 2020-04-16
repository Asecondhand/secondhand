package com.secondhand.module.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.order.dto.UpdateOrderDTO;import com.secondhand.module.order.entity.Order;import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends BaseMapper<Order> {
    Boolean updateOrder(@Param("updateOrderDTO") UpdateOrderDTO updateOrderDTO);
}