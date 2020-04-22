package com.secondhand.module.sys.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Erica
 * @since 2020/4/19
 */
@Data
public class FeedAo {
    /**
     * 反馈标题
     */
    @NotNull(message = "选项不能为空")
    private String title;

    /**
     * 问题描述
     */
    private String content;


    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String mobile;

}
