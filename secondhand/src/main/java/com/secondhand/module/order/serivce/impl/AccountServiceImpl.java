package com.secondhand.module.order.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.secondhand.module.order.entity.AccountLog;
import com.secondhand.module.order.serivce.AccountLogService;
import com.secondhand.module.order.serivce.AccountService;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-21 11:13
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    IUserService userService;
    @Autowired
    AccountLogService accountLogService;

    @Autowired
    DataSourceTransactionManager transactionManager;

    /**
     *   DefaultTransactionDefinition def = new DefaultTransactionDefinition();
     *             // explicitly setting the transaction name is something that can only be done programmatically
     *             def.setName("SomeTxName");
     *             def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
     *
     *             TransactionStatus status = transactionManager.getTransaction(def);
     *             try {
     *                 // execute your business logic here
     *                 //db operation
     *             } catch (Exception ex) {
     *                 transactionManager.rollback(status);
     *                 throw ex;
     *             }
     *         }
     */
    @Override
    @Transactional
    public boolean credit(Integer amount) {
        //开启事务
        //插入流水
        //更新余额
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                 // explicitly setting the transaction name is something that can only be done programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean result = false;
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            User currentUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserId, user.getUserId()));
            Integer logId =currentUser.getLogId();
            AccountLog accountLog = new AccountLog();
            accountLog.setAmount(amount);
            //充值使用的是 公司账户
            accountLog.setFromAccount(36);
            accountLog.setToAccount(Math.toIntExact(currentUser.getUserId()));
            accountLog.setTimestamp(new Date());
            accountLogService.save(accountLog);
            currentUser.setBalance(currentUser.getBalance().add(BigDecimal.valueOf(amount)));
            currentUser.setLogId(accountLog.getLogId());
            result = userService.update(currentUser,new LambdaUpdateWrapper<User>().eq(User::getUserId,currentUser.getUserId()).eq(User::getLogId,logId));
            if(!result){
                transactionManager.rollback(status);
            }
        }catch (RuntimeException e){
            transactionManager.rollback(status);
            throw  new ServiceException("更新失败");
        }
        return result;
    }
    @Override
    public void debit() {

    }

    @Override
    public void transfer() {

    }
}
