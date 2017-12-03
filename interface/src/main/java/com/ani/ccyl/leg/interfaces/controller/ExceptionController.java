package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihui on 17-12-3.
 */
@RestController
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    public ResponseMessageDto exceptionHandler(Exception exception) {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setState(ResponseStateEnum.ERROR);
        message.setMsg(exception.getMessage());
        exception.printStackTrace();
        return message;
    }
}
