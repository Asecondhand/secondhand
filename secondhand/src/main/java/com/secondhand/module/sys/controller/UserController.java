package com.secondhand.module.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.common.constant.Constant;
import com.secondhand.module.sys.ao.LoginAo;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IMenuService;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.impl.MenuServiceImpl;
import com.secondhand.module.sys.service.impl.UserServiceImpl;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.module.sys.vo.UserVo;
import com.secondhand.redis.RedisTool;
import com.secondhand.shiro.JwtTool;
import com.secondhand.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IMenuService iMenuService;

    @Autowired
    private RedisTool redisTool;



    //  获取列表
    @RequestMapping("/test1")
    public ApiResult test1() {
        return ApiResult.success(iUserService.test1());
    }

    @RequestMapping("/api/sys/user/test2")
    // @RequiresPermissions("user:delete")
    public PageApiResult test2() {
        Integer pageNum = 1;
        Integer PageSize = 10;
        return iUserService.test2(pageNum, PageSize);
    }



    @PostMapping("/getUserInfoWithPerms")
    public ApiResult getUserInfoWithPerms() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (null == user) return ApiResult.fail(1, "登录过期");
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (user.getUserId() == Constant.SUPER_ADMIN) {
            List<Menu> menuList = iMenuService.list();
            permsList = new ArrayList<>(menuList.size());
            for (Menu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = iUserService.queryAllPerms(user.getUserId());
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isEmpty(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        String permKey = String.format("%s%d", ShiroRealm.SECOND_HAND_PERMISSION_KEY_PREFIX, user.getUserId());
        redisTool.savePermission(permKey, permsSet);
        CurrentUserVo vo = new CurrentUserVo();
        vo.from(user);
        vo.setPerms(permsSet);
        return ApiResult.success(vo);
    }

    @GetMapping("/info")
    public ApiResult<UserVo> getUserInfo(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserVo uservo = new UserVo();
        BeanUtils.copyProperties(user, uservo);
        return ApiResult.success(uservo);
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    public ApiResult logout() {
        redisTool.clearUserInfo();
        return ApiResult.success(null);
    }

}
