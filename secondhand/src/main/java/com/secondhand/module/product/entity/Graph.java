package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "graph")
public class Graph {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Long uid;

    /**
     * 粉丝id
     */
    @TableField(value = "followid")
    private Long followid;

    /**
     *  0关注  1 取消关注
     */
    @TableField(value = "status")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_FOLLOWID = "followid";

    public static final String COL_STATUS = "status";
}