package com.secondhand.common.exception;

import lombok.Data;

/**
 *
 */

public enum ExceptionCode  implements  BaseCodeException{


    SUCCESS(0, "成功"),
    SYSTEM_BUSY(1, "系统繁忙"),
    SYSTEM_TIMEOUT(1, "服务超时"),
    PARAM_EX(1, "参数类型解析异常"),
    SQL_EX(1, "运行SQL出现异常"),
    NULL_POINT_EX(1, "空指针异常"),
    ILLEGALA_ARGUMENT_EX(1, "无效参数异常"),
    MEDIA_TYPE_EX(1, "请求类型异常"),
    LOAD_RESOURCES_ERROR(1, "加载资源出错"),
    BASE_VALID_PARAM(1, "统一验证参数异常"),
    OPERATION_EX(-1, "操作异常"),
    OK(200, "OK"),
    BAD_REQUEST(400, "错误的请求"),
    ;

    private int code;
    private String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
