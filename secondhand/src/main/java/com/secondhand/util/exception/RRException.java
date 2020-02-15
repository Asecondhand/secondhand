package com.secondhand.util.exception;

/**
 * shiro 配置异常
 * 自定义异常 ,派生了runtimeException 以保证可以解决事务回滚的问题
 * @author Erica
 * @since 2020/2/3
 */
public class RRException extends RuntimeException{
    private static final long serialVersionUID = 2233247207164868324L;

    public RRException(String msg) {
        super(msg);
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
    }

}
