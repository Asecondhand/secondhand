package com.secondhand.module.mime.mapper;

import com.secondhand.module.mime.entity.UserSale;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.mime.vo.BuyProductVO;
import com.secondhand.module.mime.vo.SaleProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户卖出的商品关联表 Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
public interface UserSaleMapper extends BaseMapper<UserSale> {

    List<SaleProductVO> getUserSaleByUserId(@Param("userId") Long userId);
}
