package com.secondhand.module.product.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-17 11:03
 **/
@Data
public class ProductDTO {


    private Integer productId;
    /**
     *
     * 用户姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户地址
     */
    @TableField(value = "user_address")
    private String userAddress;

    /**
     * 关联用户 id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 商品价格
     */
    @TableField(value = "product_price")
    private BigDecimal productPrice;

    /**
     * 商品内容
     */
    @TableField(value = "product_content")
    private String productContent;

    /**
     * 商品标签
     */
    @TableField(value = "product_tag")
    private String productTag;


    /**
     * 商品状态 0-正常 1-下架
     */
    @TableField(value = "product_status")
    private Integer productStatus;

    private Integer productNum;

    private Integer tagId;

    private String[] productPic;
}
