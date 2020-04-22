package com.secondhand.module.sys.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.ao.RoleMenuAO;
import com.secondhand.module.sys.entity.RoleMenu;
import com.secondhand.module.sys.service.IRoleMenuService;
import com.secondhand.module.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/api/roleMenu")
public class RoleMenuController {
    @Autowired
    private IRoleMenuService iRoleMenuService;

    // 权限列表 -- 到按钮级别
    @GetMapping("/list")
    public ApiResult getRoleMenuList(){
        return iRoleMenuService.getRoleMenuList();
    }

    //分配权限，传menuId
    @PostMapping("/update")
    public ApiResult updateRoleMenu(@RequestBody @Validated RoleMenuAO ao){
         iRoleMenuService.updateRoleMenu(ao);
         return ApiResult.success("成功");
    }

}
