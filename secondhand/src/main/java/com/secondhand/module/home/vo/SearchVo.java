package com.secondhand.module.home.vo;

import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.entity.UserAttr;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-18 17:08
 **/
@Data
public class SearchVo {

    List<Object> users = new ArrayList<>();

    List<Object> product = new ArrayList<>();
}
