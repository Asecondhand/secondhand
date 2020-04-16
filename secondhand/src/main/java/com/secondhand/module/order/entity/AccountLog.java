package com.secondhand.module.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "account_log")
public class AccountLog {
    /**
     * 流水号
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 交易金额
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 时间戳
     */
    @TableField(value = "timestamp")
    private Date timestamp;

    /**
     * 转出系统编码
     */
    @TableField(value = "from_system")
    private Integer fromSystem;

    /**
     * 转出系统的交易号
     */
    @TableField(value = "from_system_transaction_number")
    private Integer fromSystemTransactionNumber;

    /**
     * 转出账户
     */
    @TableField(value = "from_account")
    private Integer fromAccount;

    /**
     * 转入系统编码
     */
    @TableField(value = "to_system")
    private Integer toSystem;

    /**
     * 转入系统的交易号
     */
    @TableField(value = "to_system_transaction_number")
    private Integer toSystemTransactionNumber;

    /**
     * 转入账户
     */
    @TableField(value = "to_account")
    private Integer toAccount;

    /**
     * 交易类型编码
     */
    @TableField(value = "transaction_type")
    private Integer transactionType;

    public static final String COL_LOG_ID = "log_id";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_TIMESTAMP = "timestamp";

    public static final String COL_FROM_SYSTEM = "from_system";

    public static final String COL_FROM_SYSTEM_TRANSACTION_NUMBER = "from_system_transaction_number";

    public static final String COL_FROM_ACCOUNT = "from_account";

    public static final String COL_TO_SYSTEM = "to_system";

    public static final String COL_TO_SYSTEM_TRANSACTION_NUMBER = "to_system_transaction_number";

    public static final String COL_TO_ACCOUNT = "to_account";

    public static final String COL_TRANSACTION_TYPE = "transaction_type";
}