package com.secondhand.module.mime.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.mime.entity.UserCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.mime.vo.CollectProductVO;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户收藏的商品关联表 Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface UserCollectMapper extends BaseMapper<UserCollect> {

    List<CollectProductVO> getUserCollectByUserId(@Param("userId") Long userId);

}
