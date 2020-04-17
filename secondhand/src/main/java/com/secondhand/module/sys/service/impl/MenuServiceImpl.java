package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.constant.Constant;
import com.secondhand.common.exception.RRException;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.mapper.MenuMapper;
import com.secondhand.module.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.MenuVo;
import com.secondhand.util.shiro.ShiroUtils;
import com.secondhand.util.tree.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public ApiResult addMenu(Menu menu) {
        verifyForm(menu);
        return this.save(menu) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public ApiResult menuList() {
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
        if (!CollectionUtils.isEmpty(rootMenu)){
            Collections.sort(rootMenu, TreeUtil.order());
        }
        // 为跟菜单设置子菜单，递归调用
        for (MenuVo nav : rootMenu) {
            List<MenuVo> childMenu = TreeUtil.getChild(nav.getMenuId(), allMenu);
            // 空指针异常
            // System.out.println(childMenu);
            nav.setChildren(childMenu);
        }

        return ApiResult.success(rootMenu);
    }

    @Override
    public ApiResult updateMenu(Menu menu) {
        verifyForm(menu);
        return this.updateById(menu) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public ApiResult delMenu(Long menuId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getMenuId, menuId);
        Menu menu = new Menu();
        menu.setIsDelete(1);
        // 删除与菜单关联的角色

        return this.update(menu,queryWrapper) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }


    /**
     * 校验添加/修改菜单
     * @param menu
     */
    private void verifyForm(Menu menu){
        if(StringUtils.isBlank(menu.getMenuName())){
            throw new RRException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new RRException("上级菜单不能为空");
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            Menu parentMenu = this.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()){
            if(parentType != Constant.MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
        }

        //按钮
        if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
            if(parentType != Constant.MenuType.MENU.getValue()){
                throw new RRException("按钮上级菜单只能为菜单类型");
            }
        }
    }

}
