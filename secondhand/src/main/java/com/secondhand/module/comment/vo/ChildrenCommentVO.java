package com.secondhand.module.comment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class ChildrenCommentVO{
    private Long commentId;
    private String message;
    private Long uid;
    private String uname;
    private String icon;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    // 上一级id
    private Long commentPid;
    private Long productId;  //商品id

    // 回复的目标用户
    private Long toUid;
    private String toUname;
    private String toIcon;

}
