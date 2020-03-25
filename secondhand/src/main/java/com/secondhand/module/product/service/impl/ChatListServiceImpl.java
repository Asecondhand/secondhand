package com.secondhand.module.product.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.mapper.ChatListMapper;
import com.secondhand.module.product.entity.ChatList;
import com.secondhand.module.product.service.ChatListService;

/**
 * @author zangjan
 */
@Service
public class ChatListServiceImpl extends ServiceImpl<ChatListMapper, ChatList> implements ChatListService {

}

