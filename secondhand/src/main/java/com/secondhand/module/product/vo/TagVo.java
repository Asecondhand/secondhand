package com.secondhand.module.product.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Erica
 * @since 2020/4/18
 */
@Data
public class TagVo {

    /**
     * 商品标签id
     */
    private Integer tagId;

    /**
     * 标签名字
     */
    private String tagName;

}
