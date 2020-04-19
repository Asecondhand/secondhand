package com.secondhand.module.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Erica
 * @since 2020/4/19
 */
@Data
public class FeedbackVo {

    private Integer feedbackId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String content;

    /**
     * 处理状态 0 已处理 1 已读 2 未读
     */
    private Integer status;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
