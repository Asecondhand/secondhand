package com.secondhand.module.product.service;

import com.secondhand.module.product.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author zangjan
 */
public interface ChatMessageService extends IService<ChatMessage> {

    List<ChatMessage> getHistoryMessage(Long id);

    /**
     * 获得当前用户与用户聊天的所有数据
     * @return  Map
     */
    Map getAllHistoryMessageByCurrentId();

    /**
     * 获得 当前用户还没读取的消息
     * @return
     */
    Map getUnreadMessage();

    Boolean updateMessageToRead(Long uid);
}


