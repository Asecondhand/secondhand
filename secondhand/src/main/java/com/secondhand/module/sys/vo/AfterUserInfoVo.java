package com.secondhand.module.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Erica
 * @since 2020/4/18
 */
@Data
public class AfterUserInfoVo {
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date editTime;
    private String icon;
}
