package com.secondhand.module.comment.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class ParentCommentVO {
    private Long commentId;
    private String message;
    private Integer uid;
    private String uname;
    private String icon;
    private Date createTime;
    // 上一级id
    private Integer commentPid;
    // 回复的目标用户
    private Integer toUid;
    private String toUname;
    private String toIcon;
}
