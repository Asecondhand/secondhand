package com.secondhand.module.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-08 17:35
 **/
@Data
public class UserVo {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;
}
