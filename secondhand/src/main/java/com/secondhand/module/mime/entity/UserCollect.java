package com.secondhand.module.mime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收藏的商品关联表
 * </p>
 *
 * @author Erica
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 收藏的商品id
     */
    private Integer productId;


}
