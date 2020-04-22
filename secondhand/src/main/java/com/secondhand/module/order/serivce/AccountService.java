package com.secondhand.module.order.serivce;

/**
 * 账户服务
 * @author zangjan
 */
public interface AccountService {
    /**
     * 充值功能
     */
    boolean credit(Integer amount);

    /**
     * 提现功能
     */
    void debit();

    /**
     * 转账功能
     */
    void transfer();
}
