package com.secondhand.module.comment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long uid;
    private String uname;
    private String icon;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    // 上一级id
    private Long commentPid;
    private Long productId;  //商品id

}
