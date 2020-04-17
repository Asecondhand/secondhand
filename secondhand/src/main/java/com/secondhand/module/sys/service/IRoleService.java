package com.secondhand.module.sys.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface IRoleService extends IService<Role> {

    ApiResult findRole(String keyword);

  
    ApiResult delRole(Long roleId);

    ApiResult addRole(Role role);

    ApiResult updateRole(Role role);
}
