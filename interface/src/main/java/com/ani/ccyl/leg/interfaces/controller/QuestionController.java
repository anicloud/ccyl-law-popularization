package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.dto.QuestionVerifyDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ScoreRecordService scoreRecordService;
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseMessageDto uploadQuestionFile(QuestionTypeEnum type, MultipartFile file) {
        ResponseMessageDto message = new ResponseMessageDto();
        questionService.insertQuestionFromFile(type,file);
        message.setMsg("上传成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping("/verify")
    @ResponseBody
    public ResponseMessageDto verifyAnswer(Integer id, String answer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        ResponseMessageDto message = new ResponseMessageDto();
        QuestionDto questionDto = questionService.findById(id);
        QuestionVerifyDto verifyDto = new QuestionVerifyDto();
        message.setState(ResponseStateEnum.OK);
        if(questionDto != null && questionDto.getAnswer().equals(answer)) {
            scoreRecordService.insertScore(accountDto.getId(),5, answer, ScoreSrcTypeEnum.QUESTION,id);
            message.setMsg("正确");
            verifyDto.setCorrect(true);
        } else {
            scoreRecordService.insertScore(accountDto.getId(),0, answer, ScoreSrcTypeEnum.QUESTION,id);
            message.setMsg("错误");
            verifyDto.setCorrect(false);
            verifyDto.setAnswer(questionDto == null?null:questionDto.getAnswer());
        }
        message.setData(verifyDto);
        return message;
    }

    @RequestMapping("/findDayQuestions")
    @ResponseBody
    public ResponseMessageDto findDayQuestions(HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        List<QuestionDto> questionDtos = questionService.findDayQuestions();
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        message.setData(questionDtos);
        return message;
    }

    @RequestMapping("/findCurrentQuestion")
    @ResponseBody
    public ResponseMessageDto findCurrentQuestion(HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        QuestionDto currentQuestion = questionService.findCurrentQuestion(accountDto.getId());
        message.setData(currentQuestion);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
