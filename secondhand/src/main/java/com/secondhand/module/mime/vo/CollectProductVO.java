package com.secondhand.module.mime.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CollectProductVO extends ProductInfoVo{
    private Integer collectId;
    private Integer sellerId;  // 卖家id
    private String sellerName;
    private String sellerHeaderPicture;
    private String sellerAddress;
}
