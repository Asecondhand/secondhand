package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.CollectAO;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.mapper.UserCollectMapper;
import com.secondhand.module.mime.service.IUserCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏的商品关联表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Service
public class UserCollectServiceImpl extends ServiceImpl<UserCollectMapper, UserCollect> implements IUserCollectService {

    @Override
    public ApiResult getUserCollectByUserId(Long userId) {
        // return page.setRecords(baseMapper.getUserCollectByUserId(userId,page));
        return ApiResult.success(baseMapper.getUserCollectByUserId(userId));
    }

    /**
     * 用户收藏
     * 取消收藏
     *
     * @return
     */
    @Override
    public ApiResult UpdateUserCollectStatus(Long userId, CollectAO ao) {
        if (ao.getStatus() == 0) {
            UserCollect userCollect = new UserCollect();
            userCollect.setProductId(Math.toIntExact(ao.getProductId()));
            userCollect.setUid(Math.toIntExact(userId));
            userCollect.setProductId(Math.toIntExact(ao.getProductId()));
            userCollect.setStatus(0);
            this.save(userCollect);
        } else {
            QueryWrapper<UserCollect> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserCollect::getProductId, ao.getProductId())
                    .eq(UserCollect::getUid, userId);
            UserCollect userCollect = new UserCollect();
            userCollect.setStatus(1);
            this.update(userCollect,queryWrapper);
            // this.remove(queryWrapper);
        }
        return ApiResult.success("操作成功");
    }
}
