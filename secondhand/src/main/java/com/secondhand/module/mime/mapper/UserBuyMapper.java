package com.secondhand.module.mime.mapper;

import com.secondhand.module.mime.entity.UserBuy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.mime.vo.BuyProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户购买到的商品关联表 Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface UserBuyMapper extends BaseMapper<UserBuy> {

    List<BuyProductVO> getUserBuyByUserId(@Param("userId") Long userId);
}
