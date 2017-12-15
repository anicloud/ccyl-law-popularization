package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lihui on 17-12-14.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseMessageDto uploadQuestionFile(QuestionTypeEnum type, MultipartFile file) {
        ResponseMessageDto message = new ResponseMessageDto();
        questionService.insertQuestionFromFile(type,file);
        message.setMsg("插入成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
