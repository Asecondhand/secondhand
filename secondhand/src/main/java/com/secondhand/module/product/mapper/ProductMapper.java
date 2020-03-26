package com.secondhand.module.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.mime.vo.DynamicVO;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.vo.UserProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    List<ProductInfoVo> getProductInfoByUserId(@Param("userId") Long userId);

    List<ProductInfoVo> mineProductByUserId(@Param("userId") Long userId);

    List<DynamicVO> personalDynamic(@Param("userId") Long userId);

    List<ProductInfoVo> getProductInfoByTime(@Param("time") String time);

    Long personalDynamicAllNum(@Param("userId") Long userId);

    List<UserProductVO> getSoldOutByUserId(@Param("userId") Long userId);

    List<UserProductVO> getUserRelease(@Param("userId") Long userId);
}