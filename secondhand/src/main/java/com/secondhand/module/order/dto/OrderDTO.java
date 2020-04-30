package com.secondhand.module.order.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-16 10:32
 **/
@Data
public class OrderDTO {
    /**
     * 订单id
     */
    @TableId(value = "orderId", type = IdType.INPUT)
    private String orderid;


    private Integer productid;

    //购买者id
    private Integer uid;

    private String userAddress;
}
