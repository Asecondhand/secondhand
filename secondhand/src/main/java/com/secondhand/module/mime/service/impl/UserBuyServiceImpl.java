package com.secondhand.module.mime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.entity.UserBuy;
import com.secondhand.module.mime.mapper.UserBuyMapper;
import com.secondhand.module.mime.service.IUserBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    UserAttrService userAttrService;


    @Override
    public ApiResult getUserBuyByUserId(Long userId) {
        // List<Product> iPage = baseMapper.getUserBuyByUserId(userId,page);
        // List<Product> list = page.setRecords(baseMapper.getUserBuyByUserId(userId,page)).getRecords();
        // return page.setRecords(baseMapper.getUserBuyByUserId(userId,page));
        return ApiResult.success(baseMapper.getUserBuyByUserId(userId));
    }

    @Override
    @Transactional
    public ApiResult deleteUserBuy(Long buyId) {
        UserBuy entity = new UserBuy();
        entity.setBuyId(Math.toIntExact(buyId));
        entity.setStatus(1);
        //删除我买到的
        Long userId = ShiroUtils.getUserId();
        UserAttr userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, userId));
        Integer num = userAttr.getBuyNum();
        if (num>=1){
            userAttr.setBuyNum(num - 1);
        }else {
            userAttr.setBuyNum(0);
        }
        userAttrService.updateById(userAttr);
        return this.updateById(entity) ? ApiResult.success("操作成功") : ApiResult.fail("操作失败");
    }

}
