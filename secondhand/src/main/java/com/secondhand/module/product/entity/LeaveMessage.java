package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName(value = "leave_message")
public class LeaveMessage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 留言信息
     */
    @TableField(value = "Message")
    private String message;

    /**
     * 留言用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 留言用户姓名
     */
    @TableField(value = "uname")
    private String uname;

    /**
     * 留言用户头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 上一级留言id
     */
    @TableField(value = "message_pid")
    private Integer messagePid;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_MESSAGE = "Message";

    public static final String COL_UID = "uid";

    public static final String COL_UNAME = "uname";
}