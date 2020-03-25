package com.secondhand.module.mime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户卖出的商品关联表
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserSale implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sale_id", type = IdType.AUTO)
    private Integer saleId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 买家id
     */
    private Integer buyerId;

    /**
     * 卖出的商品id
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
