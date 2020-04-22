package com.secondhand.module.mime.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.secondhand.module.product.entity.Product;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Erica
 * @since 2020/3/18
 */
@Data
public class ProductInfoVo {
    private Integer id; // 商品id
    private String productName; // 商品名
    private String productContent;
    private BigDecimal productPrice;
    private String productPic;  // 商品图片
    private Integer productStatus;
    private String productTag;
    private String productCreateTime;
    private String userName;
    private Integer userId;  // 用户id
    private String icon;  // 头像
}
