package com.secondhand.module.sys.mapper;

import com.secondhand.module.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.sys.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<MenuVo> getMenuList(@Param("userId") Long userId);

    List<MenuVo> Allmenulist();

}
