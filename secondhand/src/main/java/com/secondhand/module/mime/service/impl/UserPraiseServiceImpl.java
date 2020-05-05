package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.PriseAO;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.entity.UserPraise;
import com.secondhand.module.mime.mapper.UserPraiseMapper;
import com.secondhand.module.mime.service.IUserPraiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    UserAttrService userAttrService;

    @Override
    public ApiResult getUserPraiseByUserId(Long userId) {
        // return page.setRecords(baseMapper.getUserPraiseByUserId(userId,page));
        return ApiResult.success(baseMapper.getUserPraiseByUserId(userId));
    }


    @Override
    @Transactional
    public ApiResult UpdateUserPraiseStatus(Long userId, PriseAO ao) {
        if (ao.getStatus() == 0) {
            QueryWrapper<UserPraise> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserPraise::getProductId, ao.getProductId())
                    .eq(UserPraise::getStatus, 0)
                    .eq(UserPraise::getUid, userId);
            UserPraise entity = this.getOne(queryWrapper);
            if (entity != null) {
                return ApiResult.success("已点过赞");
            }
            UserPraise userPraise = new UserPraise();
            userPraise.setUid(Math.toIntExact(userId));
            userPraise.setProductId(Math.toIntExact(ao.getProductId()));
            userPraise.setStatus(0);
            this.save(userPraise);
            //点赞
            UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, userId));
            Integer num = userAttr.getStarNum();
            userAttr.setSellNum(num + 1);
            userAttrService.updateById(userAttr);

        } else {
            QueryWrapper<UserPraise> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserPraise::getProductId, ao.getProductId())
                    .eq(UserPraise::getUid, userId);
            UserPraise userPraise = new UserPraise();
            userPraise.setStatus(1);
            this.update(userPraise, queryWrapper);
            //取消点赞
            UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, userId));
            Integer num = userAttr.getStarNum();
            if (num>=1){
                userAttr.setStarNum(num - 1);
            }else {
                userAttr.setStarNum(0);
            }
            userAttrService.updateById(userAttr);
        }
        return ApiResult.success("操作成功");
    }
}
