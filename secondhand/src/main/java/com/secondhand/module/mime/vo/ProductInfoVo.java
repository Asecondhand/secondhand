package com.secondhand.module.mime.vo;

import com.secondhand.module.product.entity.Product;
import lombok.Data;

/**
 * @author Erica
 * @since 2020/3/18
 */
@Data
public class ProductInfoVo extends Product {
    // 商品图片
    private String productPic;
}
