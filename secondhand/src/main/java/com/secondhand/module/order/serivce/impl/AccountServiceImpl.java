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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;
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
        //公司账户
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
            //充值使用的是 银行
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
    @Transactional
    public boolean debit(Integer amount) {
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
            accountLog.setFromAccount(Math.toIntExact(currentUser.getUserId()));
            //银行账户
            accountLog.setToAccount(36);
            accountLog.setTimestamp(new Date());
            accountLogService.save(accountLog);
            currentUser.setBalance(currentUser.getBalance().divide(BigDecimal.valueOf(amount)));
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
    @Transactional
    public boolean transfer(Integer amount , Integer fromAccountId,Integer toAccountId) {
        //检查用户是否存在
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can only be done programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean result ;
        try {
            User fromAccount = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserId, fromAccountId));
            User toAccount = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserId, toAccountId));
            if(fromAccount==null || toAccount ==null ){
                return false;
            }
            Integer fromAccountLogId =fromAccount.getLogId();
            Integer  toAccountLogId=toAccount.getLogId();
            AccountLog accountLog = new AccountLog();
            accountLog.setAmount(amount);
            //充值使用的是 公司账户
            accountLog.setFromAccount(fromAccountId);
            //收钱用户
            accountLog.setToAccount(toAccountId);
            accountLog.setTimestamp(new Date());
            accountLogService.save(accountLog);
            BigDecimal afterBalance =fromAccount.getBalance().divide(BigDecimal.valueOf(amount));
            if(afterBalance.compareTo(BigDecimal.ZERO) == -1){
                transactionManager.rollback(status);
                throw  new ServiceException("当前余额小于0");
            }
            fromAccount.setBalance(fromAccount.getBalance().divide(BigDecimal.valueOf(amount)));
            fromAccount.setLogId(accountLog.getLogId());
            result = userService.update(fromAccount,new LambdaUpdateWrapper<User>().eq(User::getUserId,fromAccount.getUserId()).eq(User::getLogId,fromAccountLogId));
            toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(amount)));
            toAccount.setLogId(accountLog.getLogId());
            result = result && userService.update(toAccount,new LambdaUpdateWrapper<User>().eq(User::getUserId,toAccount.getUserId()).eq(User::getLogId,toAccountLogId));
            if(!result){
                transactionManager.rollback(status);
            }
        }catch (RuntimeException e){
            transactionManager.rollback(status);
            throw  new ServiceException("更新失败");
        }
        return result;
    }
}
