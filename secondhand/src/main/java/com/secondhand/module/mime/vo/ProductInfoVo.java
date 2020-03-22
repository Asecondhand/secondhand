package com.secondhand.module.mime.vo;

import com.secondhand.module.product.entity.Product;
import lombok.Data;

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
    private String productPic;
    private Integer productStatus;
    private String productTag;
    private Date createTime;
}
