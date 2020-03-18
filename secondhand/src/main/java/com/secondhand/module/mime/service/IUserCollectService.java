package com.secondhand.module.mime.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.product.entity.Product;

/**
 * <p>
 * 用户收藏的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserCollectService extends IService<UserCollect> {

    IPage<Product> getUserCollectByUserId(Long userId, Page page);

    Boolean createCollect(Long userId, Long productId);

    Boolean deleteCollect(Long userId, Long productId);

    ApiResult userCollect(Long userId, Long productId);
}
