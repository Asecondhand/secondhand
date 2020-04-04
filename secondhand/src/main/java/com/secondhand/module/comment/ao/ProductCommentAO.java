package com.secondhand.module.comment.ao;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Erica
 * @since 2020/3/26
 */
@Data
public class ProductCommentAO {
    @NotNull(message = "productId 不能为空")
    private Long productId;  // 商品id
    @NotNull(message = "内容不能为空")
    private String message;  // 内容
    private Long commentPid;//上一级id
}
