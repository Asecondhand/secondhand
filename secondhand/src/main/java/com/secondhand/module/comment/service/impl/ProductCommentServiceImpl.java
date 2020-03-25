package com.secondhand.module.comment.service.impl;

import com.secondhand.module.comment.entity.ProductComment;
import com.secondhand.module.comment.mapper.ProductCommentMapper;
import com.secondhand.module.comment.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
