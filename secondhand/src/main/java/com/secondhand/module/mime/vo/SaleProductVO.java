package com.secondhand.module.mime.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SaleProductVO  extends ProductInfoVo {
    private Integer saleId;
    private Integer orderId;
    private Integer buyerId;  // 买家id
    private String buyerName;
    private String buyerHeaderPicture;
}
