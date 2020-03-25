package com.secondhand.module.product.service;

import com.secondhand.module.product.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author zangjan
 */
public interface ChatMessageService extends IService<ChatMessage> {

    List<ChatMessage> getHistoryMessage(Long id);
}


