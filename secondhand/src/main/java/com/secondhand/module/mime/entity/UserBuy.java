package com.secondhand.module.mime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户购买到的商品关联表
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserBuy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "buy_id", type = IdType.AUTO)
    private Integer buyId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 买到的商品id
     */
    private Integer productId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 是否删除
     * 0 是 1否
     */
    private Integer status;

}
