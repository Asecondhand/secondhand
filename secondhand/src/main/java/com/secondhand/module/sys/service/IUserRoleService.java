package com.secondhand.module.sys.service;

import com.secondhand.module.sys.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface IUserRoleService extends IService<UserRole> {

    void addUserRole(List<Long> roleId);
}
