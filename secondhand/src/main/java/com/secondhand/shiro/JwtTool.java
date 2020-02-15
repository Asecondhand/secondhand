package com.secondhand.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.impl.UserServiceImpl;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.redis.RedisTool;
import com.secondhand.util.shiro.ShiroUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * JwtTool
 */
@Slf4j
@Service
public class JwtTool {
    @Value("${security.token_secretKey}")
    private String secretKey;
    // 有效时长 秒为单位
    @Value("${security.token_ttl:43200}")//12小时

    private int ttl;
    @Getter
    @Resource(name = "SerializableRedisTemplate")
    private RedisTemplate redis;

    private Random rnd = new Random(System.currentTimeMillis());

    @Autowired
    private UserServiceImpl sysUserEntityService;
    @Autowired
    private RedisTool redisTool;


    public static final String SECOND_HAND_PERMISSION_KEY_PREFIX = "second_hand_";
    /**
     * 生成tokenstr
     *
     * @param userInfo
     * @param tokenKey
     * @return
     */
    public String createTokenStr(CurrentUserVo userInfo, String tokenKey) {
        // 这里使用单向加密进行加密，如果要使用不对称加密可以使用rsa
        System.out.println(userInfo);
        System.out.println("tokenKey="+tokenKey);
        Date date = new Date();

        // 计算超时时间
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.SECOND, ttl);

        return JWT.create().withSubject("shtoken").withClaim("id", tokenKey).withClaim("name", userInfo.getUserName())
                .withClaim("user_id", userInfo.getUserId())
                .withExpiresAt(cl.getTime()).sign(Algorithm.HMAC256(secretKey));
    }

    // 根据用户ID生成tokenID
    public String getTokenKey(long userId) {
        return String.format("%d_%d", System.currentTimeMillis(), userId);
    }

    /**
     * 对token进行验证
     */
    public CurrentUserVo VerifToken(String tokenStr) {
        try {
            JWTVerifier ver = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT des = ver.verify(tokenStr);
            String tokeKey = des.getClaim("id").asString();
            String name = des.getClaim("name").asString();
            Long userId = des.getClaim("user_id").asLong();

            CurrentUserVo user = new CurrentUserVo();
            user.setTokenId(tokeKey);
            user.setUserName(name);
            user.setUserId(userId);

            return user;
        } catch (TokenExpiredException e) {
            log.error("token已经过期" + e.getMessage());
        } catch (Exception ex) {
            log.error("认证token出错!" + ex.getMessage());
        }
        return null;
    }

    /**
     * 进行用户登入
     */
    public ApiResult doLogin(String strUserName, String pwd, String code) {

        //todo: 1 进行验证码认证

        //todo: 2 如果要进行密码加密，先把明文密码加密一下
        User userEntity = sysUserEntityService.getOne(new QueryWrapper<User>().lambda().
                eq(User::getUserName, strUserName), false);
        if (userEntity != null) {
            pwd = ShiroUtils.sha256(pwd, userEntity.getSalt());
        }
        //3 进行用户认证
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(strUserName, pwd));
        }catch (IllegalArgumentException e) {
            return ApiResult.fail(e.getMessage());
        }catch (Exception e) {
            return ApiResult.fail("账号密码验证失败");
        }

        CurrentUserVo userInfo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();

        String strTokenKey = userInfo.getTokenId();
        System.out.println(userInfo.getTokenId());
        // 将用户信息缓存到redis
        userInfo.setTokenId(strTokenKey);
        redisTool.saveUserInfoToRedis(userInfo);

        SecurityUtils.getSubject().hasRole("test");
        //3 生成用户token
        return ApiResult.success(this.createTokenStr(userInfo, strTokenKey));
    }

    /**
     * 刷新token
     */
    public String refreshToken() {
        CurrentUserVo userInfo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        Assert.notNull(userInfo, "当前没有用户信息，请确定用户已经登入");

        String strTokenKey = String.format("%d_%d", System.currentTimeMillis(), this.rnd.nextInt(100));
        // 将用户信息缓存到redis
        userInfo.setTokenId(strTokenKey);
        redisTool.saveUserInfoToRedis(userInfo);
        //3 生成用户token
        return this.createTokenStr(userInfo, strTokenKey);
    }



}