package com.secondhand.module.order.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.order.dto.AccountDTO;
import com.secondhand.module.order.serivce.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-16 17:44
 **/
@RequestMapping("/api/account")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @PostMapping("/credit")
    public ApiResult credit(@RequestBody AccountDTO accountDTO){
        return ApiResult.success(accountService.credit(accountDTO.getAmount()));
    }
    @PostMapping("/debit")
    public ApiResult debit(@RequestBody AccountDTO accountDTO){
        return ApiResult.success(accountService.debit(accountDTO.getAmount()));
    }
    @PostMapping("/transfer")
    public ApiResult transfer(@RequestBody AccountDTO accountDTO){
        return ApiResult.success(accountService.transfer(accountDTO.getAmount(),accountDTO.getFromAccountId(),accountDTO.getToAccountId()));
    }
}
