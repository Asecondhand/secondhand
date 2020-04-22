package com.secondhand.module.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户反馈表id
     */
    @TableId(value = "feedback_id", type = IdType.AUTO)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime;

    private Integer isDelete;
}
