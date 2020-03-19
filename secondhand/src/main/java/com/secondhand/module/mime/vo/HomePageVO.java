package com.secondhand.module.mime.vo;

import com.secondhand.module.product.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class HomePageVO  {
    private List<ProductInfoVo> productInfoVos;
    private Integer mineNum;
}
