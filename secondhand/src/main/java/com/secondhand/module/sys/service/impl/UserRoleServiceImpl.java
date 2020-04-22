package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.module.sys.entity.UserRole;
import com.secondhand.module.sys.mapper.UserRoleMapper;
import com.secondhand.module.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.util.shiro.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public void addUserRole(List<Long> roleId) {
        Long userId = ShiroUtils.getUserId();
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userId);
        this.remove(queryWrapper);
        if (CollectionUtils.isNotEmpty(roleId)) {
            roleId.forEach(item -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(Math.toIntExact(userId));
                userRole.setRoleId(Math.toIntExact(item));
                this.save(userRole);
            });
        }
    }
}
