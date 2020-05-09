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
    private Integer state;  //关注状态  0 已关注  1 未关注 2 本人
}
