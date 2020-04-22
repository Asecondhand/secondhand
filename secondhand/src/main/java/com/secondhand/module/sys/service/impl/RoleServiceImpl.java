package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Role;
import com.secondhand.module.sys.mapper.RoleMapper;
import com.secondhand.module.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public ApiResult findRole(String keyword) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotBlank(keyword),Role::getRoleName,keyword);
        List<Role> roleList = this.list(queryWrapper);
        return ApiResult.success(roleList);
    }

    @Override
    public ApiResult delRole(Long roleId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getRoleId,roleId);
        return this.remove(queryWrapper)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    @Override
    public ApiResult addRole(Role role) {
        return this.save(role)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    @Override
    public ApiResult updateRole(Role role) {
        // QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // queryWrapper.lambda().eq(Role::getRoleId,role.getRoleId());
        return this.updateById(role)?ApiResult.success("成功"):ApiResult.fail("失败");
    }
}
