package com.secondhand.module.message.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.service.ChatListService;
import com.secondhand.module.product.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/history/{uid}")
    public ApiResult chatMessageHistory(@PathVariable Long uid){
        return ApiResult.success(chatMessageService.getHistoryMessage(uid));
    }

    @GetMapping("/history")
    public ApiResult getAllMessageHistory(){
        return ApiResult.success(chatMessageService.getAllHistoryMessageByCurrentId());
    }
    /**
     * 获得一个用户还没读取的消息
     */
    @GetMapping("/unread")
    public ApiResult getUnreadMessage(){
        return ApiResult.success(chatMessageService.getUnreadMessage());
    }
    /**
     * 更新消息为已读 uid不是当前用户，而是与某人聊天的用户id
     */
    @PostMapping("/read/{uid}")
    public ApiResult updateMessageToread(@PathVariable Long uid){
        return ApiResult.success(chatMessageService.updateMessageToRead(uid));
    }
}
