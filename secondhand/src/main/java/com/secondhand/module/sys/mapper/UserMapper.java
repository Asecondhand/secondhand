package com.secondhand.module.sys.mapper;

import com.secondhand.module.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}
