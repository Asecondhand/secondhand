package com.secondhand.module.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.AddUserAO;
import com.secondhand.module.sys.ao.FindUserAo;
import com.secondhand.module.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.sys.vo.SysUserVo;

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

    ApiResult addSysUser(AddUserAO ao);

    PageApiResult findSysUser(FindUserAo keyword);

    ApiResult delSysUser(Long userId);

    ApiResult updateSysUser(AddUserAO ao);

    ApiResult listSysUser();
}
