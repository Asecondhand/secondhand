package com.secondhand.module.mime.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.mime.entity.UserPraise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.product.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户点赞的商品关联表 Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface UserPraiseMapper extends BaseMapper<UserPraise> {

    List<Product> getuserPraiseByUserId(@Param("userId") Long userId, Page page);

}
