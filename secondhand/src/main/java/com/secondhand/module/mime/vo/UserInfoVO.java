package com.secondhand.module.mime.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.secondhand.module.sys.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author Erica
 * @since 2020/3/22
 */
@Data
public class UserInfoVO {
    private Integer id;

    private Integer uid;

    /**
     * 用户性别 0-男 1-女
     */
    private Integer userSex;

    private String userResume;

    private Integer starNum;

    private Integer followNum;

    private Integer fansNum;

    private String icon;

    private String uname;

}
