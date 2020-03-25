package com.secondhand.module.comment.vo;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class ChildrenCommentVO extends ParentCommentVO {
    // 回复的目标用户
    private Integer toUid;
    private String toUname;
    private String toIcon;
}
