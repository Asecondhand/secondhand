package com.secondhand.module.sys.mapper;

import com.secondhand.module.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.sys.vo.MenuVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<MenuVo> Allmenulist();

    List<MenuVo> getMenuList(Long userId);

}
