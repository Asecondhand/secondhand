package com.secondhand.module.mime.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.mime.entity.UserBuy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.product.entity.Product;

/**
 * <p>
 * 用户购买到的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserBuyService extends IService<UserBuy> {


    IPage<Product> getUserBuyByUserId(Long userId, Page page);
}
