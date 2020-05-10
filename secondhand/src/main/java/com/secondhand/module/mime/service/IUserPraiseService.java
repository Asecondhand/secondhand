package com.secondhand.module.mime.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.PriseAO;
import com.secondhand.module.mime.entity.UserPraise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    ApiResult UpdateUserPraiseStatus(Long userId, PriseAO ao);

    List<UserPraise> getPraiseStatus(Long userId, String id);
}
