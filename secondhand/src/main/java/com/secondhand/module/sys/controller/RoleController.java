package com.secondhand.module.sys.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Role;
import com.secondhand.module.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/api/sysRole")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

   //新增  名字和备注
    @PostMapping("/add")
    public ApiResult addRole(@RequestBody Role role){
        return iRoleService.addRole(role);
    }

    // 删除
    @DeleteMapping("/del/{roleId}")
    public ApiResult delRole(@PathVariable Long roleId){
        return iRoleService.delRole(roleId);
    }

    //编辑
    @PostMapping("/update")
    public ApiResult updateRole(@RequestBody Role role){
        return iRoleService.updateRole(role);
    }

    //查找 名字
    @GetMapping("/find")
    public ApiResult findRole( String keyword){
        return iRoleService.findRole(keyword);
    }

}
