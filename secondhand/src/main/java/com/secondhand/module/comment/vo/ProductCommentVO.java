package com.secondhand.module.comment.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class ProductCommentVO {
    // 父级评论
    private ParentCommentVO ParentComments;
    //子级评论
    private List<ChildrenCommentVO> childrenComments;
}
