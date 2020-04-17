package com.secondhand.module.sys.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface IMenuService extends IService<Menu> {

    // 新增菜单
    ApiResult addMenu(Menu menu);

    ApiResult menuList();

    ApiResult updateMenu(Menu menu);

    ApiResult delMenu(Long menuId);
}
