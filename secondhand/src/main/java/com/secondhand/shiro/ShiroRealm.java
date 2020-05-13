package com.secondhand.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.constant.Constant;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IMenuService;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.impl.MenuServiceImpl;
import com.secondhand.module.sys.service.impl.UserServiceImpl;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.redis.RedisTool;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * MyRealm
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    public static final String SECOND_HAND_PERMISSION_KEY_PREFIX = "second_hand_";

    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private RedisTool redisTool;

    @Autowired
    // private UserServiceImpl sysUserEntityService;
    private IUserService iUserService;

    @Autowired
    // private MenuServiceImpl menuEntityService;
    private IMenuService iMenuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 这里第一次应该从数据库加载，第二次应该是从缓存中加载  但要处理一个过期的问题
        // System.out.println("执行授权");
        CurrentUserVo userInfo = (CurrentUserVo) principals.getPrimaryPrincipal();
        Long userId = userInfo.getUserId();
        String permKey = String.format("%s%d", SECOND_HAND_PERMISSION_KEY_PREFIX, userId);
        // System.out.println(permKey);
        Set<String> perms = redisTool.getPermissions(permKey);
        if (perms == null) {
            perms = getPermissionsFromDb(userId);
            redisTool.savePermission(permKey, perms);
        }
        // Set<String> perms = getPermissionsFromDb(userId);
        SimpleAuthorizationInfo     simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addStringPermission("admin");
        simpleAuthorizationInfo.setStringPermissions(perms);
        return simpleAuthorizationInfo;
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


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // System.out.println("执行登录");
        CurrentUserVo user = null;

        char[] pwd = null;
        if (token instanceof JwtToken) {
            user = this.processJwtAuthenticationToken(((JwtToken) token));
            // 从token获取用户信息
            // user = (CurrentUserVo)token.getPrincipal();
            // System.out.println(user);
            Assert.notNull(user, "系统中没有指定的用户");
            pwd = user.getTokenId().toCharArray();
        } else if (token instanceof UsernamePasswordToken) {
            user = this.processUserPasswordToken((UsernamePasswordToken) token);
            Assert.notNull(user, "系统中没有指定的用户");
            pwd = user.getUserPassword().toCharArray();
        } else {
            throw new AuthenticationException("没有实现的认证方式");
        }
        SimpleAuthenticationInfo info;
        if (token instanceof JwtToken) {
            info = new SimpleAuthenticationInfo(user, pwd, user.getUserName());
        } else {
            info = new SimpleAuthenticationInfo(user, pwd, getName());
        }
        return info;
    }

    /**
     * 指定支持的token类型
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken || token instanceof JwtToken;
    }


    /**
     * 处理Jwt验证
     *
     * @param token
     * @return
     */
    private CurrentUserVo processJwtAuthenticationToken(JwtToken token) {
        CurrentUserVo user = token.getUser();
        return redisTool.getUserInfo(user.getTokenId());
    }

    private CurrentUserVo processUserPasswordToken(UsernamePasswordToken token) {

        //todo: 从数据库返回用户对象 待完成 否则应该抛出异常
//        var user = new BaseUserInfo(1, token.getPrincipal().toString(), new Date(), jwtTool.getTokenKey(0));
        User userEntity = iUserService.getOne(new QueryWrapper<User>().lambda().
                eq(User::getUserName, token.getUsername()), false);
        if (null == userEntity) return null;
        CurrentUserVo user = new CurrentUserVo();
        user.from(userEntity);
        user.setTokenId(jwtTool.getTokenKey(user.getUserId()));
        return user;
    }

}