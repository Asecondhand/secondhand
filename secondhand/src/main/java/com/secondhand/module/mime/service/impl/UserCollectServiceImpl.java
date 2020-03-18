package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserCollect;
import com.secondhand.module.mime.mapper.UserCollectMapper;
import com.secondhand.module.mime.service.IUserCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.entity.Product;
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
    public IPage<ProductInfoVo> getUserCollectByUserId(Long userId, Page page) {
        return page.setRecords(baseMapper.getUserCollectByUserId(userId,page));
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
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public ApiResult userCollect(Long userId, Long productId) {
        QueryWrapper<UserCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserCollect::getUid,userId)
                .eq(UserCollect::getProductId,productId);
        UserCollect userCollect = this.getOne(queryWrapper);
        if (userCollect!=null){
            Boolean update = this.remove(queryWrapper);
            if (update==true){
                return  ApiResult.success("取消收藏成功");
            }
            return  ApiResult.success("取消收藏失败");
        }
        UserCollect collectEntity = new UserCollect();
        collectEntity.setUid(Math.toIntExact(userId));
        collectEntity.setProductId(Math.toIntExact(productId));
        Boolean save = this.save(collectEntity);
        if (save==false) {
            return ApiResult.success("收藏失败");
        }
        return ApiResult.success("收藏成功");
    }
}
