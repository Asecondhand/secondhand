package com.secondhand.module.product.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-26 20:52
 **/
@Data
public class UserAttrDTO {
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 用户性别 0-男 1-女
     */
    @TableField(value = "user_sex")
    private Integer userSex;

    /**
     * 用户简介
     */
    @TableField(value = "user_resume")
    private String userResume;

    /**
     * 点赞数量
     */
    @TableField(value = "star_num")
    private Integer starNum;

    /**
     * 关注数量
     */
    @TableField(value = "follow_num")
    private Integer followNum;

    /**
     * 粉丝数量
     */
    @TableField(value = "fans_num")
    private Integer fansNum;

    /**
     * 我发布的
     */
    @TableField(value = "publish_num")
    private Integer publishNum;

    /**
     * 我卖出的
     */
    @TableField(value = "sell_num")
    private Integer sellNum;

    /**
     * 我买到的
     */
    @TableField(value = "buy_num")
    private Integer buyNum;

    /**
     * 我收藏的
     */
    @TableField(value = "collect_num")
    private Integer collectNum;

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

    private BigDecimal balance;
}
