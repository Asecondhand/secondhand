package com.secondhand.module.product.vo;

import lombok.Data;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-13 15:33
 **/
@Data
public class UserVo {
    Long id;

    String username;

    Long noticeNum;
    /**
     * 用户头像
     */
    String icon;

}
