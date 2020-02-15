package com.secondhand.module.sys.vo;

import com.secondhand.module.sys.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Erica
 * @since 2020/2/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentUserVo extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    //存在redis 的 key 值
    private String tokenId;
    // 权限列表
    private Set<String> perms;


    public void from(User user) {
        this.setUserId(user.getUserId());
        this.setUserName(user.getUserName());
        this.setUserPassword(user.getUserPassword());
        this.setSalt(user.getSalt());
        this.setMobile(user.getMobile());
        this.setEmail(user.getEmail());
        this.setDeptId(user.getDeptId());
        this.setUserType(user.getUserType());
        this.setActualName(user.getActualName());
    }
}
