package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.module.sys.entity.User;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.ChatMessage;
import com.secondhand.module.product.mapper.ChatMessageMapper;
import com.secondhand.module.product.service.ChatMessageService;
/**
 * @author zangjan
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService{

    @Override
    public List<ChatMessage> getHistoryMessage(Long id) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user == null) {
            throw new ServiceException("用户不存在");
        }
        return this.list(new LambdaQueryWrapper<ChatMessage>().eq(ChatMessage::getToUid,id).eq(ChatMessage::getFromUid,user.getUserId()).orderByDesc(ChatMessage::getCreateTime));
    }
}
