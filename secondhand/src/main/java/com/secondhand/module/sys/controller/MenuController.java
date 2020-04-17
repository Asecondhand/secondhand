package com.secondhand.module.sys.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.service.IMenuService;
import com.secondhand.util.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/api/sysMenu")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

    //新增 /add
    @PostMapping("/add")
    public ApiResult addMenu(@RequestBody Menu menu) {
        return iMenuService.addMenu(menu);
    }


    // 列表 二级菜单 不显示按钮 -- 后台拦截按钮权限
    @GetMapping("/list")
    public ApiResult menuList() {
        return iMenuService.menuList();
    }

    //编辑 /edit
    @PostMapping("/update")
    public ApiResult updateMenu(@RequestBody Menu menu) {
        return iMenuService.updateMenu(menu);
    }


    //删除  /del
    @DeleteMapping("/del/{menuId}")
    public ApiResult delMenu(@PathVariable Long menuId){
        return iMenuService.delMenu(menuId);
    }

    // // 菜单列表
    // @GetMapping("/list")
    // public ApiResult getMenuList() {
    //     return iMenuService.menuList();
    // }

}
