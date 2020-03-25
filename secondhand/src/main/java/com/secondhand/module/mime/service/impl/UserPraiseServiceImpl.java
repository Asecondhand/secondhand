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
    public ApiResult UpdateUserPraiseStatus(Long userId, PriseAO ao) {
        if (ao.getStatus() == 1){
            UserPraise userPraise = new UserPraise();
            userPraise.setUid(Math.toIntExact(userId));
            userPraise.setProductId(Math.toIntExact(ao.getProductId()));
            this.save(userPraise);
        }else {
            QueryWrapper<UserPraise> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserPraise::getProductId,ao.getProductId())
                    .eq(UserPraise::getUid,userId);
            this.remove(queryWrapper);
        }
        return ApiResult.success("操作成功");
    }
}
