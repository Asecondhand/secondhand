package com.secondhand.module.sys.service;

import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface IUserService extends IService<User> {


    List<User> test1();

    PageApiResult test2(Integer pageNum, Integer pageSize);

    List<String> queryAllPerms(Long userId);
}
