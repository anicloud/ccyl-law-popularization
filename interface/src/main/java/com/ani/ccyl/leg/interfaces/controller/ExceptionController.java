package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihui on 17-12-3.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessageDto exceptionHandler(Exception exception) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setState(ResponseStateEnum.EXCEPTION);
        message.setMsg(exception.getMessage());
        exception.printStackTrace();
        return message;
    }
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseMessageDto runtimeExceptionHandler(Exception exception) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setState(ResponseStateEnum.EXCEPTION);
        message.setMsg(exception.getMessage());
        exception.printStackTrace();
        return message;
    }
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ResponseMessageDto loginExceptionHandler(AuthenticationException exception) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setState(ResponseStateEnum.EXCEPTION);
        message.setMsg("登录失败");
        message.setData(exception.getMessage());
        exception.printStackTrace();
        return message;
    }
}
