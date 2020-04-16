package com.secondhand.module.order.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: secondhand
 * @description:订单更新传输对象
 * @author: zangjan
 * @create: 2020-04-16 11:23
 **/
@Data
public class UpdateOrderDTO {
    /**
     * 订单id
     */
    @TableId(value = "orderId", type = IdType.INPUT)
    private String orderid;

    //创建不需要版本号
    /**
     * 0-未支付 1-已支付 2-已发货 3-已收货
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 版本号，为了防止ABA问题
     */
    @TableField(value = "version")
    private Integer version;

}
