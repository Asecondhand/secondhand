package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.ao.CollectAO;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.mapper.UserCollectMapper;
import com.secondhand.module.mime.service.IUserCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserAttrService userAttrService;

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
    @Transactional
    public ApiResult UpdateUserCollectStatus(Long userId, CollectAO ao) {
        if (ao.getStatus() == 0) {
            QueryWrapper<UserCollect> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserCollect::getProductId, ao.getProductId())
                    .eq(UserCollect::getStatus, 0)
                    .eq(UserCollect::getUid, userId);
            UserCollect entity = this.getOne(queryWrapper);
            if (entity != null) {
                return ApiResult.success("已收藏过");
            }
            UserCollect userCollect = new UserCollect();
            userCollect.setProductId(Math.toIntExact(ao.getProductId()));
            userCollect.setUid(Math.toIntExact(userId));
            userCollect.setProductId(Math.toIntExact(ao.getProductId()));
            userCollect.setStatus(0);
            this.save(userCollect);
            //添加我收藏的
            UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, userId));
            Integer num = userAttr.getCollectNum();
            userAttr.setCollectNum(num + 1);
            userAttrService.updateById(userAttr);
        } else {
            QueryWrapper<UserCollect> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserCollect::getProductId, ao.getProductId())
                    .eq(UserCollect::getUid, userId);
            UserCollect userCollect = new UserCollect();
            userCollect.setStatus(1);
            this.update(userCollect, queryWrapper);
            // 取消收藏
            UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, userId));
            Integer num = userAttr.getCollectNum();
            if (num >= 1) {
                userAttr.setCollectNum(num - 1);
            } else {
                userAttr.setCollectNum(0);
            }
            userAttrService.updateById(userAttr);
            // this.remove(queryWrapper);
        }
        return ApiResult.success("操作成功");
    }
}
