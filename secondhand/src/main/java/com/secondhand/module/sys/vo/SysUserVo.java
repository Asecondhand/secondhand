package com.secondhand.module.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Erica
 * @since 2020/4/17
 */
@Data
public class SysUserVo {
    /**
     * 用户表id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否禁用; 0-未禁用;1-禁用
     */
    private Integer status;

    /**
     * 手机号码
     */
    private String mobile;


    /**
     * 人员类型;0-超级管理员;1-普通用户
     */
    private Integer userType;

    /**
     * 真实姓名
     */
    private String username;

}
