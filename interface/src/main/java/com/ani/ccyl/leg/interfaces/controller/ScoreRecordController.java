package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.dto.TotalScoreDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-12-18.
 */
@Controller
@RequestMapping("/score")
public class ScoreRecordController {
    @Autowired
    private ScoreRecordService scoreRecordService;
    @RequestMapping(value = "/findDailyRecord", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findDailyRecord(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        List<ScoreRecordDto> scoreRecordDtos = scoreRecordService.findDailyScoreRecoreds(accountDto.getId());
        message.setData(scoreRecordDtos);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/findDailyTotalScore",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findDailyTotalScore(Integer id, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setData(id==null?scoreRecordService.findDailyTotalScore(accountDto.getId(),ScoreSrcTypeEnum.QUESTION) : id);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

    @RequestMapping(value = "/findTop20", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findDailyTop20() {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        message.setData(scoreRecordService.findDailyTop20());
        return message;
    }

    @RequestMapping(value = "/findTotalScore", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findTotalScore(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        TotalScoreDto totalScoreDto = scoreRecordService.findTotalScore(accountDto.getId());
        message.setData(totalScoreDto);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto signIn(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(accountDto.getId(),2,null, ScoreSrcTypeEnum.SIGN_IN,accountDto.getId());
        message.setState(ResponseStateEnum.OK);
        message.setMsg("签到成功");
        return message;
    }

    @RequestMapping(value = "thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto thumbUp(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(toAccountId,5,null,ScoreSrcTypeEnum.THUMB_UP,accountDto.getId());
        message.setMsg("点赞成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
}
