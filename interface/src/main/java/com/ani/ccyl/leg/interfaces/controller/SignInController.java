package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.service.service.facade.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihui on 17-12-15.
 */
@Controller
@RequestMapping("/signIn")
public class SignInController {
    @Autowired
    private SignInService signInService;
    @RequestMapping("/signIn")
    @ResponseBody
    public ResponseMessageDto signIn(HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        signInService.insertSignIn(accountDto.getId());
        message.setMsg("签到成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
