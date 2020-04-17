package com.secondhand.module.sys.ao;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/4/17
 */
@Data
public class AddUserAO {
    private Long userId;
    private String nickName;
    private String username;
    private String  password;
}
