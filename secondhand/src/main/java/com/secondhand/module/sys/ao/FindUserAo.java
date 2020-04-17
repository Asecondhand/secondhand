package com.secondhand.module.sys.ao;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/4/17
 */
@Data
public class FindUserAo {
    private String key;
    private String value;
    private Integer pageIndex;
    private Integer pageSize;
}
