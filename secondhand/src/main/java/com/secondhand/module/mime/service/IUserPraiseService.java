package com.secondhand.module.mime.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.PriseAO;
import com.secondhand.module.mime.entity.UserPraise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户点赞的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserPraiseService extends IService<UserPraise> {

    ApiResult getUserPraiseByUserId(Long userId);

    Boolean createPraise(Long userId, Long productId);

    Boolean deletePraise(Long userId, Long productId);

    ApiResult UpdateUserPraiseStatus(PriseAO ao);
}
