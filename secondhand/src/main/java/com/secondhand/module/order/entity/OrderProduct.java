package com.secondhand.module.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "order_product")
public class OrderProduct {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联订单id
     */
    @TableField(value = "orderId")
    private String orderid;

    @TableField(value = "productId")
    private Integer productid;

    public static final String COL_ID = "id";

    public static final String COL_ORDERID = "orderId";

    public static final String COL_PRODUCTID = "productId";
}