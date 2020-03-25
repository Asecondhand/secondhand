package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "chat_message")
public class ChatMessage {
    /**
     * 聊天消息
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 发送方
     */
    @TableField(value = "from_uid")
    private Integer fromUid;

    /**
     * 接受方
     */
    @TableField(value = "to_uid")
    private Integer toUid;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_FROM_UID = "from_uid";

    public static final String COL_TO_UID = "to_uid";

    public static final String COL_MESSAGE = "message";

    public static final String COL_CREATE_TIME = "create_time";
}