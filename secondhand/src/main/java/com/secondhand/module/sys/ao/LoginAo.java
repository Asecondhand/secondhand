package com.secondhand.module.sys.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Erica
 * @since 2020/2/14
 */
@Data
public class LoginAo implements Serializable {
    private String username;
    private String password;
}
