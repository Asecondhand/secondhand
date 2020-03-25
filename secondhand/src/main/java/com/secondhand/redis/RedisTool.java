package com.secondhand.redis;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.shiro.ShiroRealm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author Erica
 * @since 2020/2/15
 */
@Slf4j
@Component
public class RedisTool {

    // 有效时长 秒为单位
    @Value("${security.token_ttl:43200}")//12小时
    private int ttl;

    // redis
    @Getter
    @Resource(name = "SerializableRedisTemplate")
    private RedisTemplate redis;


    // 用redis 验证用户信息
    public void checkTokenByRedis(CurrentUserVo userInfo) {
        Assert.hasText(userInfo.getTokenId(), "token_id不能为空");
        Object value = redis.opsForValue().get(userInfo.getTokenId());
        if (value == null) throw new TokenExpiredException(
                String.format("userid=%d 的tokenkId=%s已经不存在", userInfo.getUserId(), userInfo.getTokenId()));
    }

    // 存储信息到redis
    public void saveUserInfoToRedis(CurrentUserVo userInfo) {
        org.springframework.util.Assert.notNull(userInfo, "参数userInfo不允许为空");
        org.springframework.util.Assert.hasText(userInfo.getTokenId(), "token_key不允许为空");
        // 写入redis
        redis.opsForValue().set(userInfo.getTokenId(), userInfo, this.ttl, TimeUnit.SECONDS);
    }

    // 清除用户信息
    public void clearUserInfo() {
        CurrentUserVo userInfo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        String permKey = String.format("%s%d", ShiroRealm.SECOND_HAND_PERMISSION_KEY_PREFIX, userInfo.getUserId());
        if (userInfo != null) {
            redis.opsForValue().set(userInfo.getTokenId(), null,1, TimeUnit.SECONDS);
            redis.opsForValue().set(permKey, null, 1, TimeUnit.SECONDS);
        }
        // 退出登录
        SecurityUtils.getSubject().logout();
    }


    // 获取redis缓存中的权限
    public Set<String> getPermissions(String permKey) {
        return (Set<String>) redis.opsForValue().get(permKey);
    }

    // 保存权限 到redis
    public void savePermission(String permKey, Set<String> perms) {
        redis.opsForValue().set(permKey, perms);
    }

    // 从redis 获取用户信息
    public CurrentUserVo getUserInfo(String tokenId) {
        return (CurrentUserVo) redis.opsForValue().get(tokenId);
    }


    public BoundListOperations  getRedisList(String key){
        return  redis.boundListOps(key);
    }
    /**
     * 检查是否存在该key
     */

    public boolean hasKeys(String key){
      return   redis.hasKey(key);
    }

    public boolean expire(String key,long timeout, TimeUnit timeUnit){
        return redis.expire(key,timeout,timeUnit);
    }
}
