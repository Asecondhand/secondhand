package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.entity.UserPraise;
import com.secondhand.module.mime.mapper.UserPraiseMapper;
import com.secondhand.module.mime.service.IUserPraiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.Product;
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
    public IPage<Product> getUserPraiseByUserId(Long userId, Page page) {
        return page.setRecords(baseMapper.getuserPraiseByUserId(userId,page));
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
    public ApiResult userPraise(Long userId, Long productId) {
        QueryWrapper<UserPraise> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserPraise::getUid,userId)
                .eq(UserPraise::getProductId,productId);
        UserPraise userPraise = this.getOne(queryWrapper);
        if (userPraise!=null){
            Boolean update = this.remove(queryWrapper);
            if (update==true){
                return  ApiResult.success("取消点赞成功");
            }
            return  ApiResult.success("取消点赞失败");
        }
        UserPraise praiseEntity = new UserPraise();
        praiseEntity.setUid(Math.toIntExact(userId));
        praiseEntity.setProductId(Math.toIntExact(productId));
        Boolean save = this.save(praiseEntity);
        if (save==false) {
            return ApiResult.success("点赞失败");
        }
        return ApiResult.success("点赞成功");
    }
}
