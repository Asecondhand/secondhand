package com.secondhand.module.sys.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Erica
 * @since 2020/4/13
 */
@Data
public class RoleMenuAO {
    private List<Long> menuId;
    @NotNull(message = "roleId不能为空")
    private Integer roleId;
}
