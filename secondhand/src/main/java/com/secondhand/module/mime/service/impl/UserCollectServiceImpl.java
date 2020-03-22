package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.CollectAO;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.mapper.UserCollectMapper;
import com.secondhand.module.mime.service.IUserCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public Boolean createCollect(Long userId, Long productId) {
        UserCollect userCollect = new UserCollect();
        userCollect.setProductId(Math.toIntExact(productId));
        userCollect.setUid(Math.toIntExact(userId));
        return this.save(userCollect);
    }

    @Override
    public Boolean deleteCollect(Long userId, Long productId) {
        QueryWrapper<UserCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserCollect::getUid,userId)
                .eq(UserCollect::getProductId,productId);
        return this.remove(queryWrapper);
    }

    /**
     * 用户收藏
     * 取消收藏
     * @return
     */
    @Override
    public ApiResult UpdateUserCollectStatus( CollectAO ao) {
        UserCollect collectEntity = new UserCollect();
        collectEntity.setStatus(ao.getStatus());
        collectEntity.setCollectId(Math.toIntExact(ao.getCollectId()));
        this.updateById(collectEntity);
        return ApiResult.success("成功");
    }
}
