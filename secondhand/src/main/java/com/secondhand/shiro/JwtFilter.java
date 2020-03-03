package com.secondhand.shiro;

import com.secondhand.module.sys.vo.CurrentUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * jwtFilter过滤器
 * 实现 AuthenticatingFilter 
 * @author Erica
 * @since 2020/2/14
 */
@Slf4j
@Service
public class JwtFilter extends AuthenticatingFilter {

    // 请求头部携带 token
    private final String TokenHeaderKey = "token";
    // 用户信息
    private final String UserInfoKey = "userinfo";

    @Autowired
    private JwtTool jwtTool;

    @Value("${shiro.loginUrl}")
    private String logfailUrl;

    /**
     * createToken方法获得token对象，将token对象赋值给shiro subject 对象，
     * 从而在后面的认证方法中获得token
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    //第二步
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        CurrentUserVo user = (CurrentUserVo) httpServletRequest.getAttribute(UserInfoKey);
        Assert.notNull(user, "没有当前请求的用户信息");
        // 使用自定义的认证信息，以区别用户主动发起的登入
        return new JwtToken(user);
    }

    /**
     * 根据token 先取出当前用户，然后再返回当前用户是否已认证
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    //第一步
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        // 确认当前用户是否已经有token
        String tokenValue = httpRequest.getHeader(this.TokenHeaderKey);
        if (StringUtils.hasText(tokenValue)) {
            // 存在token 对token进行验证
            CurrentUserVo user = jwtTool.VerifToken(tokenValue);
            if (user != null) {
                // 利用reqest对象传递数据
                httpRequest.setAttribute(UserInfoKey, user);
                // 执行登录
                return this.executeLogin(servletRequest, servletResponse);
            }
        }
        return writeVerfTokenFail(servletRequest, servletResponse);
    }


    /**
     * 登录失败
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private boolean writeVerfTokenFail(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 跳到失败页
        request.getRequestDispatcher(this.logfailUrl).forward(request, response);
        return false;
    }


    /**
     *判断用户是否已登录
     * 若未登录再判断是否请求的是登录地址，是登录地址则放行，否则返回false终止filter链
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }
}
