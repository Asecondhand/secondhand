package com.secondhand.module.order.serivce.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.order.mapper.AccountLogMapper;
import com.secondhand.module.order.entity.AccountLog;
import com.secondhand.module.order.serivce.AccountLogService;
@Service
public class AccountLogServiceImpl extends ServiceImpl<AccountLogMapper, AccountLog> implements AccountLogService{

}
