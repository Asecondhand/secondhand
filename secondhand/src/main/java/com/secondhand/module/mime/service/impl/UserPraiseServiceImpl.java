package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.PriseAO;
import com.secondhand.module.mime.entity.UserPraise;
import com.secondhand.module.mime.mapper.UserPraiseMapper;
import com.secondhand.module.mime.service.IUserPraiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户点赞的商品关联表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Service
public class UserPraiseServiceImpl extends ServiceImpl<UserPraiseMapper, UserPraise> implements IUserPraiseService {

    @Override
    public ApiResult getUserPraiseByUserId(Long userId) {
        // return page.setRecords(baseMapper.getUserPraiseByUserId(userId,page));
        return ApiResult.success(baseMapper.getUserPraiseByUserId(userId));
    }

    @Override
    public Boolean createPraise(Long userId, Long productId) {
        UserPraise userPraise = new UserPraise();
        userPraise.setProductId(Math.toIntExact(productId));
        userPraise.setUid(Math.toIntExact(userId));
        return  this.save(userPraise);
    }

    @Override
    public Boolean deletePraise(Long userId, Long productId) {
        QueryWrapper<UserPraise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserPraise::getUid,userId)
                .eq(UserPraise::getProductId,productId);
        return this.remove(queryWrapper);
    }

    @Override
    public ApiResult UpdateUserPraiseStatus(PriseAO ao) {
        UserPraise userPraise = new UserPraise();
        userPraise.setStatus(ao.getStatus());
        userPraise.setPraiseId(Math.toIntExact(ao.getPriseId()));
        this.updateById(userPraise);
        return ApiResult.success("成功");
    }
}
