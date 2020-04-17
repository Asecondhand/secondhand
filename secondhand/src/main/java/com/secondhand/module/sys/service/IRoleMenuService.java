package com.secondhand.module.sys.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.ao.RoleMenuAO;
import com.secondhand.module.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    ApiResult getRoleMenuList();

    void updateRoleMenu(RoleMenuAO ao);
}
