package com.secondhand.module.message.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.service.ChatListService;
import com.secondhand.module.product.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-03-25 10:58
 **/

@RequestMapping("/api/message/chat")
@RestController
public class ChatMessageController {

    @Autowired
    ChatMessageService chatMessageService;
    @Autowired
    ChatListService chatListService;
    @RequestMapping("/history/{uid}")
    public ApiResult chatMessageHistory(@PathVariable Long uid){
        return ApiResult.success(chatMessageService.getHistoryMessage(uid));
    }
}
