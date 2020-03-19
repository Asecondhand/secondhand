package com.secondhand.module.mime.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
public class DynamicVO {
    private Integer num;
    private String title;
    private String time;
    private List<ProductInfoVo> productList;
}
