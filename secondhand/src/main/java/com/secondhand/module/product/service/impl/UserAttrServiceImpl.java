package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.mime.vo.UserInfoVO;
import com.secondhand.module.sys.entity.User;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.mapper.UserAttrMapper;
import com.secondhand.module.sys.service.UserAttrService;

@Service
public class UserAttrServiceImpl extends ServiceImpl<UserAttrMapper, UserAttr> implements UserAttrService {

    @Override
    public UserAttr getCurrentUserInfo() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return this.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, user.getUserId()));
    }

    @Override
    public boolean changeIcon(String icon) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserAttr userAttr = this.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, user.getUserId()));
        userAttr.setIcon(icon);
        return this.updateById(userAttr);
    }

    @Override
    public ApiResult getUserInfoByUserId(Long uid) {
        UserAttr userAttr = this.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid, uid));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties( userAttr,userInfoVO);
        return ApiResult.success(userInfoVO);
    }
}





