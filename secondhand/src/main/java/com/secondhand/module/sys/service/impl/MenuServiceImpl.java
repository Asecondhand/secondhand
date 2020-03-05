package com.secondhand.module.sys.service.impl;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.Menu;
import com.secondhand.module.sys.mapper.MenuMapper;
import com.secondhand.module.sys.mapper.UserMapper;
import com.secondhand.module.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.service.IRoleMenuService;
import com.secondhand.module.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
