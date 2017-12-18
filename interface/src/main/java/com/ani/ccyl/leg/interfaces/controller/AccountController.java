package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihui on 17-12-13.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/saveSelfInfo")
    @ResponseBody
    public ResponseMessageDto saveSelfInfo(AccountDto accountDto) {
        ResponseMessageDto message = new ResponseMessageDto();
        accountService.saveSelfInfo(accountDto);
        return message;
    }
}
