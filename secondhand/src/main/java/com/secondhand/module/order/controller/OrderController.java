package com.secondhand.module.order.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.order.dto.OrderDTO;
import com.secondhand.module.order.dto.UpdateOrderDTO;
import com.secondhand.module.order.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-15 16:35
 **/
@RequestMapping("/api/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    /**
     * 解决重复提交订单问题
     * 生成订单id
     */
    @GetMapping("/getId")
    public ApiResult createOrderId(@RequestBody OrderDTO orderDTO){
        return ApiResult.success(createRandomId(orderDTO.getProductid().toString()));
    }

    /**
     * 提交订单
     * @param
     * @return
     */
    @PostMapping
    public ApiResult createOrderc(@RequestBody OrderDTO orderDTO){
        return ApiResult.success(orderService.createOrder(orderDTO));
    }


    /**
     * 查询订单
     * @return
     */
    /**
     * 支付订单
     * @return
     */
    @PostMapping("/pay")
    public ApiResult payOrderc(@RequestBody OrderDTO orderDTO ){
        return  ApiResult.success(orderService.payOrder(orderDTO));
    }
    /**
     * 更新订单
     * @return
     */
    @PutMapping
    public ApiResult updateOrderc(@RequestBody UpdateOrderDTO updateOrderDTO){
        return ApiResult.success(orderService.updateOrder(updateOrderDTO));
    }
    /**
     * 订单id +随机6位数+商品id+时间日期
     */
    private String createRandomId(String productId){
        StringBuffer stringBuffer = new StringBuffer();
        //返回0.0-1.0
        Random random = new Random();
        for (int i = 0; i <6 ; i++) {
            int a =  random.nextInt(10);
            stringBuffer.append(a);
        }
        stringBuffer.append(productId);
        LocalDateTime localDateTime = LocalDateTime.now();
        stringBuffer.append(localDateTime.toEpochSecond(ZoneOffset.of("+8")));
        return stringBuffer.toString();
    }
}
