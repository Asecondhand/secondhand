package com.secondhand.module.comment.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.comment.ao.ProductCommentAO;
import com.secondhand.module.comment.entity.ProductComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品留言表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
public interface IProductCommentService extends IService<ProductComment> {

    ApiResult getProductComment(Long productId);

    ApiResult addProductComment(ProductCommentAO ao);
}
