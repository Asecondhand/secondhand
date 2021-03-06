package com.secondhand.module.mime.service;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.CollectAO;
import com.secondhand.module.mime.entity.UserCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户收藏的商品关联表 服务类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
public interface IUserCollectService extends IService<UserCollect> {

   ApiResult getUserCollectByUserId(Long userId);

    ApiResult UpdateUserCollectStatus(Long userId, CollectAO ao);

    List<UserCollect> getCollectStatus(Long userId, String id);
}
