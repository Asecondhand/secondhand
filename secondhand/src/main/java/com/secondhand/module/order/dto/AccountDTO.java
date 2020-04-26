package com.secondhand.module.order.dto;

import lombok.Data;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-21 15:54
 **/
@Data
public class AccountDTO {
    Integer amount;

    Integer fromAccountId;

    Integer toAccountId;
}
