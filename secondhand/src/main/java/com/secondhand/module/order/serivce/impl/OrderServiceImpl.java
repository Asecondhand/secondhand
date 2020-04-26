package com.secondhand.module.order.serivce.impl;

import com.secondhand.module.order.dto.OrderDTO;
import com.secondhand.module.order.dto.UpdateOrderDTO;
import com.secondhand.module.order.entity.OrderProduct;
import com.secondhand.module.order.serivce.OrderProductService;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.redis.RedisTool;
import com.secondhand.util.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.order.mapper.OrderMapper;
import com.secondhand.module.order.entity.Order;
import com.secondhand.module.order.serivce.OrderService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author zangjan
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    ProductService productService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    RedisTool redisTool;
    @Autowired
    @Resource(name = "SerializableRedisTemplate")
    RedisTemplate redisTemplate;
    //订单关联商品id
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        OrderProduct orderProduct = new OrderProduct();
        Product product = productService.getById(orderDTO.getProductid());
        if(product == null || !redisTool.hasKeys("productNum")){
            return  false;
        }
        //更改库存这个操作要加锁
        HashOperations hashOperations = redisTemplate.opsForHash();
        Integer productNumber = (Integer) hashOperations.get("productNum",orderDTO.getProductid());
        if(productNumber <=0){
            throw  new ServiceException("商品数量不足");
        }
        redisTemplate.watch("productNum");
        redisTemplate.multi();
        hashOperations.put("productNumb", orderDTO.getProductid(), productNumber-1);
        List<Object> result = redisTemplate.exec();
        if(result.size() ==0 ){
            return false;
        }
        order.setOrderid(orderDTO.getOrderid());
        //初始化订单状态和版本
        order.setStatus(0);
        order.setVersion(0);
        this.save(order);
        orderProduct.setOrderid(orderDTO.getOrderid());
        orderProduct.setProductid(orderDTO.getProductid());
        return orderProductService.save(orderProduct);
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

