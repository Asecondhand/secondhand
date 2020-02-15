package com.secondhand.shiro;

import com.secondhand.module.sys.vo.CurrentUserVo;
import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.util.Assert;

/**
 * token  认证载体
 * 实现 AuthenticationToken 用于收集用户信息
 * @author Erica
 * @since 2020/2/14
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 9095438849271248106L;

    @Getter
    private CurrentUserVo user;

    /**
     * 构造函数
     */
    public JwtToken() {
    }

    /**
     * 构造一个 token 认证主题
     * @param user
     */
    public JwtToken(CurrentUserVo user) {
        Assert.notNull(user,"参数user不允许为空");
        this.user = user;
    }

    /**
     * 用户信息
     * @return
     */
    @Override
    public Object getPrincipal() {
        return user;
    }

    /**
     * 用户凭据 tokenId 确认用户携带的token 认证
     * @return
     */
    @Override
    public Object getCredentials() {
        return user.getTokenId();
    }
}
