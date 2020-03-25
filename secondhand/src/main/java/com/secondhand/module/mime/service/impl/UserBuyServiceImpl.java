package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserBuy;
import com.secondhand.module.mime.mapper.UserBuyMapper;
import com.secondhand.module.mime.service.IUserBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户购买到的商品关联表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Service
public class UserBuyServiceImpl extends ServiceImpl<UserBuyMapper, UserBuy> implements IUserBuyService {

    @Override
    public ApiResult getUserBuyByUserId(Long userId) {
        // List<Product> iPage = baseMapper.getUserBuyByUserId(userId,page);
        // List<Product> list = page.setRecords(baseMapper.getUserBuyByUserId(userId,page)).getRecords();
        // return page.setRecords(baseMapper.getUserBuyByUserId(userId,page));
        return ApiResult.success(baseMapper.getUserBuyByUserId(userId));
    }

    @Override
    public ApiResult deleteUserBuy(Long buyId) {
        UserBuy entity = new UserBuy();
        entity.setBuyId(Math.toIntExact(buyId));
        entity.setStatus(1);
        return this.updateById(entity) ? ApiResult.success("操作成功") : ApiResult.fail("操作失败");
    }

}
