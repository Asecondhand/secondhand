package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "chat_list")
public class ChatList {
    /**
     * 用户聊天列表
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 聊天列表用户id
     */
    @TableField(value = "to_uid")
    private Integer toUid;

    /**
     * 当前用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 聊天用户名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_NAME = "name";

    public static final String COL_ICON = "icon";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}