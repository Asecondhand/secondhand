package com.secondhand.module.sys;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @program: secondhand3
 * @description:用户注册
 * @author: zangjan
 * @create: 2020-03-12 16:12
 **/
@Data
public class UserDTO {
    @NotNull
    String username;
    @Size(min = 6,max = 20)
    String password;

    @Email
    String email;

    String mobile;

    String icon;


}
