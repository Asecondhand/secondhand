package com.secondhand.module.comment.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品留言表
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品留言表id
     */
    private Integer commentId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 留言信息
     */
    private String message;

    /**
     * 留言用户id
     */
    private Integer uid;

    /**
     * 留言用户姓名
     */
    private String uname;

    /**
     * 留言用户头像
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 上一级留言id
     */
    private Integer commentPid;

    /**
     * 是否删除 0是 1否
     */
    private Integer isDelete;
}
