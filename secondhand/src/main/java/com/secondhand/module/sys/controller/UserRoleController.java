package com.secondhand.module.sys.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.ao.UserRoleAO;
import com.secondhand.module.sys.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/api/userRole")
public class UserRoleController {
    @Autowired
    private IUserRoleService iUserRoleService;

    // 新增/更新 分配角色
    @PostMapping("/update")
    public ApiResult addUserRole(@RequestBody UserRoleAO ao){
        // 批量插入
        iUserRoleService.addUserRole(ao.getRoleId());
        return  ApiResult.success("成功");
    }
}
