package com.secondhand.module.mime.service.impl;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserSale;
import com.secondhand.module.mime.mapper.UserSaleMapper;
import com.secondhand.module.mime.service.IUserSaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户卖出的商品关联表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
@Service
public class UserSaleServiceImpl extends ServiceImpl<UserSaleMapper, UserSale> implements IUserSaleService {

    @Override
    public ApiResult getUserSaleByUserId(Long userId) {
        return ApiResult.success(baseMapper.getUserSaleByUserId(userId));
    }
}
