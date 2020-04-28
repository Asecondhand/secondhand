package com.secondhand.module.sys.controller;

import com.alibaba.fastjson.JSONObject;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.es.EsIndex;
import com.secondhand.module.sys.UserDTO;
import com.secondhand.module.sys.ao.LoginAo;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.redis.RedisTool;
import com.secondhand.shiro.JwtTool;
import com.secondhand.util.exception.ServiceException;
import com.secondhand.util.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * @author Erica
 * @since 2020/3/7
 */
@RestController
public class LoginController {

    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private RedisTool redisTool;
    @Autowired
    private IUserService userService;

    @Autowired
    private UserAttrService userAttrService;

    @Autowired
    RestHighLevelClient restHighLevelClient;


    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody LoginAo po/*, String captcha, String uuid*/) {
        if (StringUtils.isEmpty(po.getUsername()) || StringUtils.isEmpty(po.getPassword())) {
            return ApiResult.fail("参数格式不正确");
        }
        ApiResult object = jwtTool.doLogin(po.getUsername(), po.getPassword(), "");
        String token = object.getMessage();
        int code = object.getCode();
        String msg = object.getMessage();
        CurrentUserVo userVo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        if (code == 0) {
            msg = "登录成功";
            // 更新时间
            User user = new User();
            user.setUserId(ShiroUtils.getUserId());
            user.setEditTime(new Date());
            user.setViewNum(userVo.getViewNum() + 1);
            userService.updateById(user);
        } else {
            token = null;
        }
        return ApiResult.success(code, msg, token);
    }

    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ApiResult registerUser(@Validated @RequestBody UserDTO userDTO) {
        String[] strings = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"
                , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "w", "e", "r", "t", "y", "z", "x"
        };
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            stringBuilder.append(strings[random.nextInt(strings.length)]);
        }
        userDTO.setPassword(ShiroUtils.sha256(userDTO.getPassword(), stringBuilder.toString()));
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setUserPassword(userDTO.getPassword());
        user.setSalt(stringBuilder.toString());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setIcon(userDTO.getIcon() == null ? "http://47.93.117.14:8080/second-hand/fileSystem/files/images.jpg" : userDTO.getIcon());
//        user.seticon(userDTO.getHeadPicture()==null?"http://localhost:9091/second-hand/fileSystem/files/1238390565725274112.jpg":userDTO.getHeadPicture());
        //添加 userattr 属性
        try {
            userService.save(user);
        } catch (Exception ignored) {
            return ApiResult.fail(1, "用户名重复，请检查输入");
        }
        BulkRequest request = new BulkRequest();
        UserAttr userAttr = new UserAttr();
        userAttr.setIcon(user.getIcon());
        userAttr.setUname(user.getUserName());
        userAttr.setUid(Math.toIntExact(user.getUserId()));
        //默认男
        userAttr.setUserSex(0);
        //个人简介
        userAttr.setUserResume("这个人很懒，什么都没留下");
        userAttrService.save(userAttr);
        String json = JSONObject.toJSONString(userAttr);
        request.add(new IndexRequest(EsIndex.USERINDEX.getIndexName()).source(json, XContentType.JSON));
        //抛出异常回滚
        try {
            BulkResponse bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            return ApiResult.success(!bulkResponse.hasFailures());
        } catch (IOException ex) {
            throw new ServiceException("es添加失败");
        }
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    public ApiResult logout() {
        redisTool.clearUserInfo();
        return ApiResult.success(null);
    }

}
