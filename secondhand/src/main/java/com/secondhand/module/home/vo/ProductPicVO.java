package com.secondhand.module.home.vo;

import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.entity.ProductPic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-30 14:37
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPicVO  extends Product {

    List<String> picList;

}
