package com.secondhand.module.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.sys.ao.FindUserAo;
import com.secondhand.module.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.sys.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface UserMapper extends BaseMapper<User> {

    List<String> queryAllPerms(Long userId);

    IPage<SysUserVo> findAllUser(Page page, FindUserAo ao);

    IPage<SysUserVo> findAllUser1(Page page, FindUserAo ao);

    IPage<SysUserVo> findUserByUsername(Page page, FindUserAo ao);

    IPage<SysUserVo> findUserBynickName(Page page, FindUserAo ao);

}
