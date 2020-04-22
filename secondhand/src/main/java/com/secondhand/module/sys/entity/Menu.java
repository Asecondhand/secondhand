package com.secondhand.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Erica
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 前端路由
     */
    private String url;

    private String permission;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 顺序
     */
    private Integer orderNum;

    private String icon;

    /**
     * 是否删除
     * 0 正常
     * 1 删除
     */
    private Integer isDelete;

}
