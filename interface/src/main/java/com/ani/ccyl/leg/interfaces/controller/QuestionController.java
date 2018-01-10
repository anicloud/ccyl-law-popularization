package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.dto.QuestionVerifyDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import com.ani.ccyl.leg.service.service.facade.ShareRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
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
    @Autowired
    private ShareRelationService shareRelationService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessageDto uploadQuestionFile(@RequestParam QuestionTypeEnum type, @RequestParam MultipartFile file) {
        ResponseMessageDto message = new ResponseMessageDto();
        questionService.insertQuestionFromFile(type,file);
        message.setMsg("上传成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto verifyAnswer(Integer id, String answer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        ResponseMessageDto message = new ResponseMessageDto();
        if(StringUtils.isEmpty(answer)) {
            message.setState(ResponseStateEnum.ERROR);
            message.setMsg("答案不能为空");
            return message;
        }
        QuestionDto questionDto = questionService.findById(id);
        QuestionVerifyDto verifyDto = new QuestionVerifyDto();
        message.setState(ResponseStateEnum.OK);
        if(questionDto != null && questionDto.getAnswer().equalsIgnoreCase(answer)) {
            scoreRecordService.insertScore(accountDto.getId(),Constants.Score.QUESTION_SCORE, answer, ScoreSrcTypeEnum.QUESTION,id);
            message.setMsg("正确");
            verifyDto.setIsCorrect(true);
        } else {
            scoreRecordService.insertScore(accountDto.getId(),0, answer, ScoreSrcTypeEnum.QUESTION,id);
            message.setMsg("错误");
            verifyDto.setIsCorrect(false);
            verifyDto.setAnswer(questionDto == null?null:questionDto.getAnswer());
        }
      //  Integer orderNum=questionService.findOrderNumbyId(id);
//        if (orderNum == 5){
//
////           ShareRelationPO shareRelationPO = shareRelationService.selectBySharedId(accountDto.getId());
////            if (shareRelationPO!=null){
////                scoreRecordService.insertScore(shareRelationPO.getShareId(),Constants.Score.);
////            }
////
//        }
        message.setData(verifyDto);
        return message;
    }

    @RequestMapping(value = "/findDayQuestions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findDayQuestions(HttpServletRequest request) throws ParseException {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        List<QuestionDto> questionDtos = questionService.findDayQuestions();
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        message.setData(questionDtos);
        return message;
    }

    @RequestMapping(value = "/findCurrentQuestion", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findCurrentQuestion(HttpServletRequest request) throws ParseException {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        QuestionDto currentQuestion = questionService.updateNewQuestion(accountDto.getId());
        message.setData(currentQuestion);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
