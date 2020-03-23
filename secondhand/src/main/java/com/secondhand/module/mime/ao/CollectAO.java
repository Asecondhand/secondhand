package com.secondhand.module.mime.ao;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
public class CollectAO {
    private Integer status;  // 0 取消 1收藏
    private Long productId; // 商品id
}
