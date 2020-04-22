package com.secondhand.common.permissions;

import com.secondhand.common.constant.Constant;
import com.secondhand.common.exception.RRException;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IMenuService;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.redis.RedisTool;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;


@Component
@Aspect
// @Order(200)
public class PermissionAop {
    public static final String SECOND_HAND_PERMISSION_KEY_PREFIX = "second_hand_";

    @Autowired
    private RedisTool redisTool;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IMenuService iMenuService;

    @Pointcut(value = "@annotation(com.secondhand.common.permissions.Permission)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        // 检查用户是否登录
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            throw new RRException("请先登录",401);
            // return ApiResult.fail(401,"没有登录");
        }
        // 当前用户的权限
        Long userId = user.getUserId();
        String permKey = String.format("%s%d", SECOND_HAND_PERMISSION_KEY_PREFIX, userId);
        // System.out.println(permKey);
        Set<String> perms = redisTool.getPermissions(permKey);
        if (perms == null) {
            perms = getPermissionsFromDb(userId);
            redisTool.savePermission(permKey, perms);
        }
        // 获取切入的Method
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        String[] permissions = permission.value();

        //判断权限
        boolean result = checkPermission(perms, permissions);
        if (result) {
            return point.proceed();
        } else {
            // return  ApiResult.fail(401,"权限不足");
            throw new RRException("没有权限",401);
        }
    }

    private boolean checkPermission(Set<String> perms, String[] permissions) {
        List<String> list = Arrays.asList(permissions);
        String per = list.get(0);
        if (perms.contains(per)){
            return true;
        }
        return false;
    }

    public Set<String> getPermissionsFromDb(Long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<Menu> menuList = iMenuService.list();
            permsList = new ArrayList<>(menuList.size());
            for (Menu menu : menuList) {
                permsList.add(menu.getPermission());
            }
        } else {
            permsList = iUserService.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isEmpty(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }
}
