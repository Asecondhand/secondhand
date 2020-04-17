package com.secondhand.module.sys.vo;

import com.secondhand.module.sys.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Erica
 * @since 2020/4/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuVo extends Menu {
    private List<MenuVo> children;



}
