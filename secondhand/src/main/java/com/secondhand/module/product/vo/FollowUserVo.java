package com.secondhand.module.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-16 16:29
 **/
@Data
public class FollowUserVo {
    @TableField(value = "uid")
    private Long id;

    /**
     * 用户性别
     */
    @TableField(value = "user_sex")
    private Integer userSex;

    /**
     * 用户简介
     */
    @TableField(value = "user_resume")
    private String userResume;


    /**
     * 用户头像//默认头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 用户名称
     */
    @TableField(value = "uname")
    private String uname;

    /**
     * 用户关注状态 默认0为已关注
     */
    private int status=0;
}
