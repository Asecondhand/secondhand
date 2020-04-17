package com.secondhand.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.constant.Constant;
import com.secondhand.module.sys.ao.RoleMenuAO;
import com.secondhand.module.sys.entity.RoleMenu;
import com.secondhand.module.sys.mapper.RoleMenuMapper;
import com.secondhand.module.sys.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.MenuVo;
import com.secondhand.util.shiro.ShiroUtils;
import com.secondhand.util.tree.TreeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public ApiResult getRoleMenuList() {
        Long userId = ShiroUtils.getUserId();
        List<MenuVo> allMenu = new ArrayList<>();
        if (userId == Constant.SUPER_ADMIN) {
            allMenu = baseMapper.Allmenulist();
        } else {
            allMenu = baseMapper.getMenuList(userId);
        }
        // 根节点
        List<MenuVo> rootMenu = new ArrayList<>();
        for (MenuVo nav : allMenu) {
            if (nav.getParentId() == 0) {
                rootMenu.add(nav);
            }
        }
        // 根据MenuVo的order_num排序
        if (CollectionUtils.isNotEmpty(rootMenu)){
            Collections.sort(rootMenu, TreeUtil.order());
        }
        // 为跟菜单设置子菜单，递归调用
        for (MenuVo nav : rootMenu) {
            List<MenuVo> childMenu = TreeUtil.getChild(nav.getMenuId(), allMenu);
            nav.setChildren(childMenu);
        }
        return ApiResult.success(rootMenu);
    }


    @Override
    public void updateRoleMenu(@RequestBody RoleMenuAO ao) {
        // 判断不为空 先删除再新增
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenu::getRoleId, ao.getRoleId());
        this.remove(queryWrapper);
        // 插入
        if (CollectionUtils.isNotEmpty(ao.getMenuId())) {
            ao.getMenuId().forEach(item -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(ao.getRoleId());
                roleMenu.setRoleMenuId(item);
                this.save(roleMenu);
            });
        }
    }
}
