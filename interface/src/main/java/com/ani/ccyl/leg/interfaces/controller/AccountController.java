package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.SMSUtil;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @ResponseBody
    @RequestMapping("/sendSMSCode")
    public ResponseMessageDto sendSMSCode(String phone, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        Long startTime = (Long) session.getAttribute(Constants.SMS_CODE_TIME_SESSION);
        if(startTime == null||(System.currentTimeMillis()-startTime)<60L*1000L) {
            SMSUtil.sendSMSCode(phone);
            session.setAttribute(Constants.SMS_CODE_TIME_SESSION, System.currentTimeMillis());
            message.setState(ResponseStateEnum.OK);
            message.setMsg("发送成功");
        } else {
            message.setState(ResponseStateEnum.ERROR);
            message.setMsg("请在一分钟后重发");
        }
        return message;
    }

    @RequestMapping("/verifyCode")
    @ResponseBody
    public ResponseMessageDto verifySMSCode(String phone, String code) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setData(SMSUtil.verifyCode(phone,code));
        message.setMsg("验证码");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
