package com.secondhand.module.mime.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PriseProductVO extends ProductInfoVo {
    private Integer praiseId;
}
