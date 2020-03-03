package com.secondhand.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "leave_message")
public class LeaveMessage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 用户姓名
     */
    @TableField(value = "uname")
    private String uname;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_MESSAGE = "Message";

    public static final String COL_UID = "uid";

    public static final String COL_UNAME = "uname";
}