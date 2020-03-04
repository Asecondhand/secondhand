package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@TableName(value = "product")
public class Product {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户地址
     */
    @TableField(value = "user_address")
    private String userAddress;

    /**
     * 关联用户 id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 商品价格
     */
    @TableField(value = "product_price")
    private BigDecimal productPrice;

    /**
     * 商品内容
     */
    @TableField(value = "product_content")
    private String productContent;

    /**
     * 商品标签
     */
    @TableField(value = "product_tag")
    private String productTag;

    /**
     * 商品创建时间
     */
    @TableField(value = "product_create_time",fill = FieldFill.INSERT)
    private Date productCreateTime;

    /**
     * 商品状态 0-正常 1-下架
     */
    @TableField(value = "product_status")
    private Integer productStatus;

    /**
     * 记录创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 记录更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除 0-正常 1-删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_ADDRESS = "user_address";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PRODUCT_NAME = "product_name";

    public static final String COL_PRODUCT_PRICE = "product_price";

    public static final String COL_PRODUCT_CONTENT = "product_content";

    public static final String COL_PRODUCT_TAG = "product_tag";

    public static final String COL_PRODUCT_CREATE_TIME = "product_create_time";

    public static final String COL_PRODUCT_STATUS = "product_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETED = "is_deleted";
}