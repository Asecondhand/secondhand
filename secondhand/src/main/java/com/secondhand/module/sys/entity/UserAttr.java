package com.secondhand.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

@Data
@TableName(value = "user_attr")
public class UserAttr {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

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

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;


    @TableField(value = "sold_num")
    private Integer soldNum;


    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_USER_SEX = "user_sex";

    public static final String COL_USER_RESUME = "user_resume";

    public static final String COL_STAR_NUM = "star_num";

    public static final String COL_FOLLOW_NUM = "follow_num";

    public static final String COL_FANS_NUM = "fans_num";

    public static final String COL_PUBLISH_NUM = "publish_num";

    public static final String COL_SELL_NUM = "sell_num";

    public static final String COL_BUY_NUM = "buy_num";
    public static final String COL_SOLD_NUM = "sold_num";

    public static final String COL_COLLECT_NUM = "collect_num";

    public static final String COL_ICON = "icon";

    public static final String COL_UNAME = "uname";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}