package com.secondhand.common.config;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.exception.ExceptionCode;
import com.secondhand.common.exception.RRException;
import com.secondhand.common.permissions.PermissionException;
import com.secondhand.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: secondhand3
 * @description:异常处理信息返回
 * @author: zangjan
 * @create: 2020-03-16 09:52
 **/
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionConfig {


    /**
     * 空指针异常处理
     * @param ex
     * @param httpServletRequest
     * @return
     */
//    @ExceptionHandler(NullPointerException.class)
//    public ApiResult nullPointerException(NullPointerException ex, HttpServletRequest httpServletRequest){
//        log.warn("NullPointerException :" +ex);
//        return ApiResult.fail(ExceptionCode.NULL_POINT_EX.getCode(),ExceptionCode.NULL_POINT_EX.getMessage()).setPath(httpServletRequest.getRequestURI());
//    }

    /**
     * sql运行异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public ApiResult sqlException(SQLException ex , HttpServletRequest request){
        log.warn("SqlException:" +ex);
        return ApiResult.fail(ExceptionCode.SQL_EX.getCode(),ExceptionCode.SQL_EX.getMessage()).setPath(request.getRequestURI());
    }

    /**
     * 参数校验异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ApiResult.fail(ExceptionCode.BASE_VALID_PARAM.getCode(), message).setPath(request.getRequestURI());
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResult serviceException(ServiceException ex, HttpServletRequest request) {
        log.warn("ConstraintViolationException:", ex);
        return ApiResult.fail(ExceptionCode.LOAD_RESOURCES_ERROR.getCode(), ex.getMessage()).setPath(request.getRequestURI());
    }

    @ExceptionHandler(PermissionException.class)
    public ApiResult permissionException(PermissionException ex,HttpServletRequest request){
        log.warn("PermissionException:",ex);
        return ApiResult.fail(ex.getCode(),ex.getMessage());
    }


    @ExceptionHandler(RRException.class)
    public ApiResult RRException(RRException ex,HttpServletRequest request){
        log.warn("PermissionException:",ex);
        return ApiResult.fail(ex.getCode(),ex.getMessage());
    }

}
