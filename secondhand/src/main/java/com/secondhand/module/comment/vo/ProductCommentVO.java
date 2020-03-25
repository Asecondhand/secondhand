package com.secondhand.module.comment.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Erica
 * @since 2020/3/25
 */
@Data
public class ProductCommentVO {
    private Long productId;  //商品id
    private ParentCommentVO ParentComments;
    private List<ChildrenCommentVO> childrenComments;
}
