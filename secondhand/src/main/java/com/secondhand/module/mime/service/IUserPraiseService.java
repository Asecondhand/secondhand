package com.secondhand.module.mime.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserPraise;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.entity.Product;

/**
 * <p>
 * 用户点赞的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserPraiseService extends IService<UserPraise> {

    IPage<ProductInfoVo> getUserPraiseByUserId(Long userId, Page page);

    Boolean createPraise(Long userId, Long productId);

    Boolean deletePraise(Long userId, Long productId);

    ApiResult userPraise(Long userId, Long productId);
}
