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
@TableName(value = "graph")
/**
 * 关注与被关注
 */
public class Graph {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 粉丝id
     */
    @TableField(value = "followid")
    private Integer followid;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_FOLLOWID = "followid";
}