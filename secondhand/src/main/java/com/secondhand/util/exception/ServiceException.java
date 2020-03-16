package com.secondhand.util.exception;

import com.secondhand.util.support.IResultCode;
import com.secondhand.util.support.ResultCode;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-02-27 15:37
 **/
public class ServiceException extends  RuntimeException {

    private static final long serialVersionUID = 2359767895161832954L;
    private final IResultCode resultCode;

    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.FAILURE;
    }

    public ServiceException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ServiceException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public IResultCode getResultCode() {
        return this.resultCode;
    }
}
