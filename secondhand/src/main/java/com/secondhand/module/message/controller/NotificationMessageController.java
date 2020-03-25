package com.secondhand.module.message.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.message.service.NotificationMessageService;
import com.secondhand.module.message.vo.GraphMessageVO;
import com.secondhand.module.product.service.GraphService;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.product.vo.FollowUserVo;
import com.secondhand.module.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-03-23 10:28
 **/
@RestController
@RequestMapping("/api/message/notification")
public class NotificationMessageController {


    @Autowired
    NotificationMessageService notificationMessageService;
    /**
     * 上线 实时通知
     */
    @GetMapping("/{id}")
    public ApiResult  notificationMessage(@PathVariable Long id){
        return ApiResult.success(notificationMessageService.updateMessage(id));
    }
}
