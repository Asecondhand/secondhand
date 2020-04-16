package com.secondhand.module.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @author zangjan
 */
@Data
@TableName(value = "`order`")
public class Order {
    /**
     * 订单id
     */
    @TableId(value = "orderid", type = IdType.INPUT)
    private String orderid;

    /**
     * 下单时间
     */
    @TableField(value = "create_time")
    private Date createTime;

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

    public static final String COL_ORDERID = "orderid";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_STATUS = "status";

    public static final String COL_VERSION = "version";
}