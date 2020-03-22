package com.secondhand.module.mime.ao;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/3/19
 */
@Data
public class PriseAO {
    private Long PriseId;
    private Integer status;  // 0 取消 1点赞
}
