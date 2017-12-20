package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.SMSUtil;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-13.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/saveSelfInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessageDto saveSelfInfo(@RequestBody AccountDto accountDto) {
        ResponseMessageDto message = new ResponseMessageDto();
        accountService.saveSelfInfo(accountDto);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("保存成功");
        return message;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseMessageDto findById(Integer id) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = accountService.findById(id);
        message.setData(accountDto);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
    @RequestMapping("/findProvinces")
    @ResponseBody
    public ResponseMessageDto findProvinces() {
        ResponseMessageDto message = new ResponseMessageDto();
        Map<String,Object> resultMap = new HashMap<>();
        for(ProvinceEnum province:ProvinceEnum.values()) {
            resultMap.put("name",province.name());
            resultMap.put("value",province.getValue());
        }
        message.setData(resultMap);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
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

    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto verifySMSCode(String phone, String code) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setData(SMSUtil.verifyCode(phone,code));
        message.setMsg("验证码");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
