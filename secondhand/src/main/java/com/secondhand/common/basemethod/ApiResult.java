package com.secondhand.common.basemethod;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

/**
 * Api返回结果
 *
 * @author Erica
 * @since 2020/2/3
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
public class ApiResult<T> {
    private static final long serialVersionUID = -5963397321998888554L;

    private int code;
    // // 错误代码
    // private String errorCode;
    // 错误消息
    private String message;
    // 返回的对象
    private T data;

    private  String path;

    public static int FAIL_CODE = 1;

    public static int SUCCESS_CODE = 0;
    public ApiResult(int code, String message, T data) {
        this.code = code;
        // 2位十进制数
        // this.errorCode = String.format("%02d", code);
        this.message = message;
        this.data = data;
    }

    public ApiResult(int code, String message) {
        this.code = code;
        // this.errorCode = String.format("%02d", code);
        this.message = message;
    }

    public static <T> ApiResult fail(int code, String message) {

        Assert.isTrue(code >= FAIL_CODE, "错误结果请不要设置code值为0");

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
    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<>(FAIL_CODE, message);
    }

    /**
     * 构造一个成功结果
     *
     * @param data
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> re = new ApiResult<>(SUCCESS_CODE, "成功");
        re.data = data;
        return re;
    }

    public static <T> ApiResult<T> success(String msg) {
        return new ApiResult<>(SUCCESS_CODE, msg);
    }

    public static <T> ApiResult<T> success(boolean flag){
        return  flag ? success("操作成功") : fail("操作失败");
    }
    /**
     * 构造一个成功结果
     *
     * @param data
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> ApiResult success(String msg, T data) {
        ApiResult<T> re = new ApiResult<>(SUCCESS_CODE, msg);
        re.data = data;
        return re;
    }

    public static <T> ApiResult<T> success(Integer code,String msg,T data) {
        return new ApiResult<>(code,msg,data);
    }
}
