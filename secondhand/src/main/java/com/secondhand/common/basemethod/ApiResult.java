package com.secondhand.common.basemethod;

import lombok.Data;
import org.springframework.util.Assert;

/**
 * Api返回结果
 *
 * @author Erica
 * @since 2020/2/3
 */
@Data
public class ApiResult<T> {
    private static final long serialVersionUID = -5963397321998888554L;

    private int code;
    // // 错误代码
    // private String errorCode;
    // 错误消息
    private String errorMessage;
    // 返回的对象
    private T returnObject;

    public ApiResult(int code, String errorMsg, T data) {
        this.code = code;
        // 2位十进制数
        // this.errorCode = String.format("%02d", code);
        this.errorMessage = errorMsg;
        this.returnObject = data;
    }

    public ApiResult(int code, String errorMsg) {
        this.code = code;
        // this.errorCode = String.format("%02d", code);
        this.errorMessage = errorMsg;
    }

    public static <T> ApiResult fail(int code, String message) {

        Assert.isTrue(code > 0, "错误结果请不要设置code值为0");

        return new ApiResult(code, message);
    }

    /**
     * 构造一个标准错误
     *
     * @param message
     * @return
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static <T> ApiResult fail(String message) {
        return new ApiResult(500, message);
    }

    /**
     * 构造一个成功结果
     *
     * @param data
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> ApiResult success(T data) {
        ApiResult<T> re = new ApiResult<>(0, "成功");
        re.returnObject = data;
        return re;
    }

    /**
     * 构造一个成功结果
     *
     * @param data
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> ApiResult success(String errorMsg, T data) {
        ApiResult<T> re = new ApiResult<>(0, errorMsg);
        re.returnObject = data;
        return re;
    }
}
