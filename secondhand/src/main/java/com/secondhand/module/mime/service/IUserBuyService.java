package com.secondhand.module.mime.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserBuy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户购买到的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserBuyService extends IService<UserBuy> {


   ApiResult getUserBuyByUserId(Long userId);

    ApiResult deleteUserBuy(Long buyId);
}
