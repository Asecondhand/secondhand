package com.secondhand.module.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.module.product.entity.ChatList;
import com.secondhand.module.product.entity.ChatMessage;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.service.ChatListService;
import com.secondhand.module.product.service.ChatMessageService;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.util.exception.ServiceException;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-03-24 16:11
 **/
@ServerEndpoint("/chat/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    private static volatile  int onlineNum = 0;

    /**
     * 建立 user 和 websocket 映射
     */
    private static Map<String,WebSocketServer> webSocketServerMap =new ConcurrentHashMap<>();

    /**
     * session 通信需要
     */
    private Session session;

    /**
     * 保存用户id
     */
    private String userId = "";

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    ChatListService chatListService;

    @Autowired
    UserAttrService userAttrService;

    private static int getOnlineNum() {
        return onlineNum;
    }

    private static synchronized  void addOnlineNum() {
        WebSocketServer.onlineNum ++;
    }
    private static synchronized  void subOnlineNum() {
        WebSocketServer.onlineNum --;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String  userId){
        this.session  = session;
        this.userId = userId;
        webSocketServerMap.put(userId,this);
        addOnlineNum();
        log.info("当前连接的人数"+getOnlineNum());
    }
    @OnClose
    public void OnClose(){
        webSocketServerMap.remove(this.userId);
        subOnlineNum();
        log.info("当前连接的人数"+getOnlineNum());
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    @OnMessage
    public void OnMessage(String message, Session session){
        if( message!=null && message.length() > 0){
            log.info("收到来自"+session.getBasicRemote()+"的消息:"+message);
            JSONObject jsonObject = JSON.parseObject(message);
            String userId =jsonObject.getString("userId");
            //如果接受方的 列表没有发送方的信息，添加进去
            if(chatListService.getOne(new LambdaQueryWrapper<ChatList>().eq(ChatList::getToUid,this.userId).eq(ChatList::getUid,userId))==null){
                ChatList chatList = new ChatList();
                UserAttr userAttr = userAttrService.getById(this.userId);
                if(userAttr == null)
                    throw new ServiceException("查找的用户id不存在");
                chatList.setToUid(Integer.valueOf(this.userId));
                chatList.setUid(Integer.valueOf(userId));
                chatList.setName(userAttr.getUname());
                chatList.setIcon(userAttr.getIcon());
                chatListService.save(chatList);
            }
            if(chatListService.getOne(new LambdaQueryWrapper<ChatList>().eq(ChatList::getToUid,userId).eq(ChatList::getUid,this.userId))==null){
                ChatList chatList = new ChatList();
                UserAttr userAttr = userAttrService.getById(userId);
                if(userAttr == null)
                    throw new ServiceException("查找的用户id不存在");
                chatList.setToUid(Integer.valueOf(userId));
                chatList.setUid(Integer.valueOf(this.userId));
                chatList.setName(userAttr.getUname());
                chatList.setIcon(userAttr.getIcon());
                chatListService.save(chatList);
            }
            if(userId!=null &&userId.length()>0 && webSocketServerMap.containsKey(userId)){
                try {
                    webSocketServerMap.get(userId).sendMessage(jsonObject.toJSONString());
                } catch (IOException e) {
                    log.error("发送消息出错"+e.getMessage());
                }
            }else{
                //保存到数据库中
                log.info("请求的user不在线上"+userId);
            }
            saveChatMessage(message);
        }

    }
    /**
     * 时间戳，form用户id， to用户id,内容
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private void saveChatMessage(String message){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setToUid(Integer.valueOf(userId));
        chatMessage.setFromUid(Integer.valueOf(this.userId));
        chatMessage.setMessage(message);
        chatMessageService.save(chatMessage);
    }

}
