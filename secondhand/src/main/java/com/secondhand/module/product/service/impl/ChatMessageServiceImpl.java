package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.module.product.entity.ChatList;
import com.secondhand.module.product.service.ChatListService;
import com.secondhand.module.sys.entity.User;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.ChatMessage;
import com.secondhand.module.product.mapper.ChatMessageMapper;
import com.secondhand.module.product.service.ChatMessageService;
/**
 * @author zangjan
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService{

    @Autowired
    ChatListService chatListService;
    @Override
    public List<ChatMessage> getHistoryMessage(Long id) {
        //首先找到 跟当前用户聊天的列表id
        //通过聊天列表id，将数据返回给前端
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user == null) {
            throw new ServiceException("用户不存在");
        }
        return this.list(new LambdaQueryWrapper<ChatMessage>().eq(ChatMessage::getToUid,id).eq(ChatMessage::getFromUid,user.getUserId()).orderByDesc(ChatMessage::getCreateTime));
    }

    @Override
    public Map getAllHistoryMessageByCurrentId() {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if(user == null) {
            throw new ServiceException("用户不存在");
        }
        List<ChatList> chatLists = chatListService.list(new LambdaQueryWrapper<ChatList>().eq(ChatList::getUid,user.getUserId()));
        Map<String,List<ChatMessage>> map = new HashMap<>();
        chatLists.forEach(chatList -> {
            List ids = Arrays.asList(chatList.getToUid(),user.getUserId());
            //拒绝自己给自己发消息
            List<ChatMessage> chatMessages  = this.list(new LambdaQueryWrapper<ChatMessage>().in(ChatMessage::getFromUid,ids).in(ChatMessage::getToUid,ids));
            map.put(String.valueOf(chatList.getToUid()),chatMessages);
        });
        return map;
    }

    @Override
    public Map getUnreadMessage() {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if(user == null) {
            throw new ServiceException("用户不存在");
        }
        List<ChatList> chatLists = chatListService.list(new LambdaQueryWrapper<ChatList>().eq(ChatList::getUid,user.getUserId()));
        Map<String,List<ChatMessage>> map = new HashMap<>();
        chatLists.forEach(chatList -> {
            List ids = Arrays.asList(chatList.getToUid(),user.getUserId());
            //todo 拒绝自己给自己发消息
            //获得未读的消息
            List<ChatMessage> chatMessages  = this.list(new LambdaQueryWrapper<ChatMessage>().in(ChatMessage::getFromUid,ids).in(ChatMessage::getToUid,ids).eq(ChatMessage::getStatus,0));
            map.put(String.valueOf(chatList.getToUid()),chatMessages);
        });
        return map;
    }

    @Override
    public Boolean updateMessageToRead(Long uid) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if(user == null) {
            throw new ServiceException("用户不存在");
        }
        //将当前用户与 uid 所有的message status 都设置为 read 1
        //将 uid 发送给当前用户的所有message 都设为 已读
        //todo 使用异步去做
        List<ChatMessage> chats = this.list(new LambdaQueryWrapper<ChatMessage>().eq(ChatMessage::getToUid,user.getUserId()).eq(ChatMessage::getFromUid,uid));
        chats = chats.stream().peek(chatMessage -> chatMessage.setStatus(1)).collect(Collectors.toList());
        return this.saveBatch(chats);
    }
}
