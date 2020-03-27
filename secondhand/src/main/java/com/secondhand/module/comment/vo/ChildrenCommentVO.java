package com.secondhand.module.comment.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ChildrenCommentVO extends ParentCommentVO {
    // 回复的目标用户
    private Long toUid;
    private String toUname;
    private String toIcon;
}
