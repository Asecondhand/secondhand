package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.AddUserAO;
import com.secondhand.module.sys.ao.FindUserAo;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.mapper.UserMapper;
import com.secondhand.module.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.SysUserVo;
import com.secondhand.util.shiro.ShiroUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> test1() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda()
                .eq(User::getStatus, 0);
        List<User> list = this.list(userQueryWrapper);
        return list;
    }

    @Override
    public PageApiResult test2(Integer pageNum, Integer pageSize) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda()
                .eq(User::getStatus, 0);
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = this.page(page, userQueryWrapper);
        return new PageApiResult(iPage);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return this.baseMapper.queryAllPerms(userId);
    }

    @Override
    public ApiResult addSysUser(AddUserAO ao) {
        User userEntity = this.getOne(new QueryWrapper<User>().lambda().
                eq(User::getUserName, ao.getNickName()));
        if (userEntity != null) {
            return ApiResult.fail("用户已存在");
        }

        String[] strings = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"
                , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "w", "e", "r", "t", "y", "z", "x"
        };
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            stringBuilder.append(strings[random.nextInt(strings.length)]);
        }
        String pwd = ShiroUtils.sha256(ao.getPassword(), stringBuilder.toString());
        System.out.println(pwd);
        User user = new User();

        user.setUserPassword(ShiroUtils.sha256(ao.getPassword(), stringBuilder.toString()));
        user.setSalt(stringBuilder.toString());
        user.setUserType(2);
        user.setBalance(BigDecimal.valueOf(0));
        user.setLogId(0);
        user.setUserName(ao.getNickName());
        user.setActualName(ao.getUsername());
        user.setCreateTime(new Date());
        user.setEditTime(new Date());
        return this.save(user) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public PageApiResult findSysUser(FindUserAo ao) {
        IPage<SysUserVo> iPage = null;
        Page page = new Page<>(ao.getPageIndex(), ao.getPageSize());
        if (StringUtils.isBlank(ao.getValue())) {
            iPage = baseMapper.findAllUser(page, ao);
        }
        if (StringUtils.isNotBlank(ao.getValue()) && ao.getKey().equals("all")) {
            iPage = baseMapper.findAllUser1(page, ao);
        }
        if (StringUtils.isNotBlank(ao.getValue()) && ao.getKey().equals("username")) {
            iPage = baseMapper.findUserByUsername(page, ao);
        }
        if (StringUtils.isNotBlank(ao.getValue()) && ao.getKey().equals("nickName")) {
            iPage = baseMapper.findUserBynickName(page, ao);
        }
        return new PageApiResult(iPage);
    }

    @Override
    public ApiResult delSysUser(Long userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserId, userId);
        if (this.getOne(queryWrapper) == null) {
            return ApiResult.fail("查不到删除的信息");
        }
        return this.remove(queryWrapper) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public ApiResult updateSysUser(AddUserAO ao) {

        User entity = this.getById(ao.getUserId());
        if (entity == null) {
            return ApiResult.fail("用户不存在");
        }
        User userEntity = this.getOne(new QueryWrapper<User>().lambda().
                eq(User::getUserName, ao.getNickName()));
        if (userEntity != null && !userEntity.getUserId().equals(ao.getUserId())) {
            return ApiResult.fail("用户已存在");
        }
        if (ao.getUserId() == null) {
            return ApiResult.fail("userId为空");
        }

        User user = new User();
        if (StringUtils.isNotBlank(ao.getPassword())) {
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setUserPassword(ShiroUtils.sha256(ao.getPassword(), user.getSalt()));
        }
        user.setUserId(ao.getUserId());
        if (StringUtils.isNotBlank(ao.getNickName())) {
            user.setUserName(ao.getNickName());
        }
        if (StringUtils.isNotBlank(ao.getUsername())) {
            user.setActualName(ao.getUsername());
        }
        user.setEditTime(new Date());
        return this.updateById(user) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public ApiResult listSysUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getStatus, 0)
                .eq(User::getStatus, 0)
                .eq(User::getUserType, 2);
        ;
        List<User> list = this.list(queryWrapper);
        return ApiResult.success(list);
    }


}
