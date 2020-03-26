package com.secondhand.module.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.comment.entity.ProductComment;
import com.secondhand.module.comment.mapper.ProductCommentMapper;
import com.secondhand.module.comment.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.comment.vo.ParentCommentVO;
import com.secondhand.module.comment.vo.ProductCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品留言表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements IProductCommentService {

    // @Override
    // public ApiResult getProductComment(Long productId) {
    //     // 查询商品所有父级评论
    //     List<ProductComment> parentComments = getParentComments(productId);
    //     // 查询商品所有的子级评论
    //     // List<ProductComment> childrenComments = getChildrenComments(productId);
    //     // 得到评论list
    //     // List<ProductCommentVO> productCommentVOS = getProductComments(parentComments, childrenComments);
    //     return null;
    // }
    //
    // /**
    //  * 获取商品所有的评论
    //  *
    //  * @param parentComments
    //  * @param childrenComments
    //  * @return
    //  */
    // private List<ProductCommentVO> getProductComments(List<ProductComment> parentComments, List<ProductComment> childrenComments) {
    //     List<ProductCommentVO> comments = new ArrayList<>();
    //     // 只有父级评论
    //     if (parentComments.size() > 0 && childrenComments.size() <= 0) {
    //         for (int i = 0; i < parentComments.size(); i++) {
    //             // 将父级评论添加到出参实体中
    //             ProductCommentVO vos = new ProductCommentVO();
    //             ParentCommentVO parentComment = new ParentCommentVO();
    //             BeanUtils.copyProperties(parentComments.get(i), parentComment);
    //             vos.setParentComments(parentComment);
    //             comments.add(vos);
    //         }
    //     }
    //     if (parentComments.size() > 0 && childrenComments.size() > 0) {
    //         for (int i = 0; i < parentComments.size(); i++) {
    //             for (int j = 0; j < childrenComments.size(); j++) {
    //                 // if (parentComments.get(i))
    //             }
    //         }
    //     }
    //
    //     return comments;
    // }
    //
    // // 查询商品所有父级评论
    // List<ProductComment> getParentComments(Long productId) {
    //     QueryWrapper<ProductComment> queryWrapper = new QueryWrapper<>();
    //     queryWrapper.lambda()
    //             .eq(ProductComment::getProductId, productId)
    //             .eq(ProductComment::getCommentId, 0)
    //             .orderByDesc(ProductComment::getCreateTime);
    //     List<ProductComment> productComments = this.list(queryWrapper);
    //     return productComments;
    // }
    //
    // // 查询商品所有的子级评论
    // // List<ProductComment> getChildrenComments(Long productId) {
    // //     // List<ProductComment> productComments =
    // //     return productComments;
    // // }
}
