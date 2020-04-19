package com.secondhand.module.sys.ao;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author Erica
 * @since 2020/4/18
 */
@Data
public class NoticeAo {
    private String title;
    private String content;
}
