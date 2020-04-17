package com.secondhand.module.sys.ao;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/4/9
 */
@Data
public class MenuAO {
    private Long pid;  //父级id
    private String menuName; // 菜单名字
    private String url; // 前端路由
    private String permission; // 权限
    private Integer type;  // 类型 0 菜单 1 一级菜单 2 按钮
    private Integer orderNum; // 顺序

}
