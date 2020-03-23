package com.secondhand.module.mime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户点赞的商品关联表
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserPraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "praise_id", type = IdType.AUTO)
    private Integer praiseId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 点赞的商品id
     */
    private Integer productId;


}
