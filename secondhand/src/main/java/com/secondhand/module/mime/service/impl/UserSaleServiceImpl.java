package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserSale;
import com.secondhand.module.mime.mapper.UserSaleMapper;
import com.secondhand.module.mime.service.IUserSaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户卖出的商品关联表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-19
 */
@Service
public class UserSaleServiceImpl extends ServiceImpl<UserSaleMapper, UserSale> implements IUserSaleService {

    @Autowired
    UserAttrService userAttrService;
    @Override
    public Boolean addSaleProduct(Long userId, Integer productId,String orderId,Long buyId) {
        UserSale userSale = new UserSale();
        userSale.setBuyerId(Math.toIntExact(buyId));
        userSale.setOrderId(orderId);
        userSale.setProductId(productId);
        userSale.setStatus(1);
        //卖家id
        userSale.setUid(Math.toIntExact(userId));
        this.save(userSale);
        UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid,userId));
//        UserAttr userAttr=userAttrService.getUserAttrByuid(userId);
        Integer sellNum = userAttr.getSellNum();
        userAttr.setSellNum(sellNum+1);
        return userAttrService.update(userAttr,new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getSellNum,sellNum));
    }

    @Override
    public ApiResult getUserSaleByUserId(Long userId) {
        return ApiResult.success(baseMapper.getUserSaleByUserId(userId));
    }

    @Override
    @Transactional
    public ApiResult deleteUserSale(Long saleId) {
        UserSale userSale = new UserSale();
        userSale.setSaleId(Math.toIntExact(saleId));
        userSale.setStatus(1);
        // 我卖出的
        Long userId = ShiroUtils.getUserId();
        UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid,userId));
        Integer saleNum = userAttr.getSellNum();
        userAttr.setSellNum(saleNum-1);
        userAttrService.updateById(userAttr);
        return this.updateById(userSale) ? ApiResult.success("操作成功") : ApiResult.fail("操作失败");
    }
}
