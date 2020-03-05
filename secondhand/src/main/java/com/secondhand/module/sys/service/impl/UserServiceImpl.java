package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.mapper.UserMapper;
import com.secondhand.module.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> test1() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda()
                .eq(User::getStatus,0);
        List<User> list = userMapper.selectList(userQueryWrapper);
        return list;
    }

    public PageApiResult test2(Integer pageNum, Integer pageSize) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda()
                .eq(User::getStatus,0);
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<User> iPage = userMapper.selectPage(page,userQueryWrapper);
        return new PageApiResult(iPage);
    }


    public List<String> queryAllPerms(Long userId) {
        return this.userMapper.queryAllPerms(userId);
    }
}
