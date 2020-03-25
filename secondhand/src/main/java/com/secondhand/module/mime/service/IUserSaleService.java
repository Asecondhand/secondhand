package com.secondhand.module.mime.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserSale;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户卖出的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
public interface IUserSaleService extends IService<UserSale> {

    ApiResult getUserSaleByUserId(Long userId);

    ApiResult deleteUserSale(Long saleId);
}
