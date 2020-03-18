package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName(value = "product_pic")
public class ProductPic {

    public ProductPic(Integer pid, String productPic){
        this.pid = pid;
        this.productPic = productPic;
    }
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
        @TableField(value = "pid")
        private Integer pid;

    /**
     * 图片地址
     */
    @TableField(value = "product_pic")
    private String productPic;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_PRODUCT_PIC = "product_pic";
}