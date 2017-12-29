package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.LocationUtil;
import com.ani.ccyl.leg.commons.utils.SMSUtil;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResponseMessageDto saveSelfInfo(@RequestBody AccountDto accountDto, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto loginDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        accountDto.setId(loginDto.getId());
        accountService.saveSelfInfo(accountDto);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("保存成功");
        return message;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findById(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setData(accountService.findById(accountDto.getId()));
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
    @RequestMapping(value = "/findProvinces",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findProvinces() {
        ResponseMessageDto message = new ResponseMessageDto();
        List<Map> resultList = new ArrayList<>();
        for(ProvinceEnum province:ProvinceEnum.values()) {
            Map<String,Object> provinceMap = new HashMap<>();
            provinceMap.put("name",province.name());
            provinceMap.put("value",province.getValue());
            resultList.add(provinceMap);
        }
        message.setData(resultList);
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

    @RequestMapping(value = "/findInfoIsCompleted", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findInfoIsCompleted(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setMsg("查询成功");
        message.setData(accountService.findIsInfoCompleted(accountDto.getId()));
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/updateProvince", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto updateProvince(String log, String lat, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        ProvinceEnum add = LocationUtil.getAdd(log, lat);
        accountDto.setProvince(add);
        accountService.saveSelfInfo(accountDto);
        message.setMsg("更新成功");
        message.setState(ResponseStateEnum.OK);
        message.setData(add);
        return message;
    }
}
