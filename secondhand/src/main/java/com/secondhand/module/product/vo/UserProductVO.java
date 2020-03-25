package com.secondhand.module.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class UserProductVO {
    private Integer id; // 商品id
    private String productName; // 商品名
    private String productContent;
    private BigDecimal productPrice;
    private String productPic;  // 商品图片
    private Integer productStatus;
    private String productTag;
    private String productCreateTime;
    private Integer userId;  // 用户id
}
