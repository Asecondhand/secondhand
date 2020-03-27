package com.secondhand.module.comment.mapper;

import com.secondhand.module.comment.entity.ProductComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.comment.vo.ChildrenCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品留言表 Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
public interface ProductCommentMapper extends BaseMapper<ProductComment> {

    List<ChildrenCommentVO> getChildrenComments(@Param("productId") Long productId);
}
