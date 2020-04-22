package com.secondhand.common.permissions;

import com.secondhand.common.exception.ExceptionCode;
import com.secondhand.util.support.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.omg.CORBA.NO_PERMISSION;

/**
 * @author Erica
 * @since 2020/4/13
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PermissionException extends RuntimeException {

    private Integer code;
    private String message;

    public PermissionException(String message,Integer code) {
        super(message);
        this.message=message;
        this.code=code;
    }

    public PermissionException(Exception ex) {
        super(ex.getMessage());
    }

}
