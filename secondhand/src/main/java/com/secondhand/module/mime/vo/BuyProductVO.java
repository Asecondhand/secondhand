package com.secondhand.module.mime.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 我买到的商品出参
 * @author Erica
 * @since 2020/3/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BuyProductVO extends ProductInfoVo{
    private Integer buyId;
    private Integer orderId;
    private Integer sellerId;  // 卖家id
    private String sellerName;
    private String sellerHeaderPicture;
    private String sellerAddress;
}
