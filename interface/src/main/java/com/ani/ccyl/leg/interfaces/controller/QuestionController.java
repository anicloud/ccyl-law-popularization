package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lihui on 17-12-14.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @RequestMapping("/upload")
    public ResponseMessageDto uploadQuestionFile(QuestionTypeEnum type, MultipartFile file) {
        ResponseMessageDto message = new ResponseMessageDto();

        return message;
    }
}
