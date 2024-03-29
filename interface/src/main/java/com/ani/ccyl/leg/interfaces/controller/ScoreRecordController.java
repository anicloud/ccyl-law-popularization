package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import com.ani.ccyl.leg.service.service.facade.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lihui on 17-12-18.
 */
@Controller
@RequestMapping("/score")
public class ScoreRecordController {
    @Autowired
    private ScoreRecordService scoreRecordService;
    @Autowired
    private TimerTaskService timerTaskService;

//    @RequestMapping(value = "/findDailyTotalScore",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseMessageDto findDailyTotalScore(Integer id, HttpSession session) {
//        ResponseMessageDto message = new ResponseMessageDto();
//        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
//        DailyTotalScoreDto scoreDto = scoreRecordService.findDailyTotalScore(id == null?accountDto.getId():id,ScoreSrcTypeEnum.QUESTION);
//        message.setState(ResponseStateEnum.OK);
//        message.setData(scoreDto);
//        message.setMsg("查询成功");
//        return message;
//    }

    @RequestMapping(value = "/findTop20", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findDailyTop20() throws UnsupportedEncodingException {
        ResponseMessageDto message = new ResponseMessageDto();
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        message.setData(scoreRecordService.findDailyTop20());
        return message;
    }

    @RequestMapping(value = "/findTotalScore", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findTotalScore(HttpSession session) throws UnsupportedEncodingException {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        TotalScoreDto totalScoreDto = scoreRecordService.findTotalScore(accountDto.getId());
        if (totalScoreDto != null) {
            totalScoreDto.setNickName(accountDto.getNickName());
            totalScoreDto.setPortrait(accountDto.getPortrait());
        }
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
        scoreRecordService.insertScore(accountDto.getId(), Constants.Score.SIGN_IN_SCORE, null, ScoreSrcTypeEnum.SIGN_IN, accountDto.getId());
        TotalScoreDto totalScore = scoreRecordService.findTotalScore(accountDto.getId());
        if (totalScore != null) {
            totalScore.setPortrait(accountDto.getPortrait());
            totalScore.setNickName(accountDto.getNickName());
        }
        message.setState(ResponseStateEnum.OK);
        message.setData(totalScore);
        message.setMsg("签到成功");
        return message;
    }

    @RequestMapping(value = "/isSignIn", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto isSignIn(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        message.setData(scoreRecordService.findIsSignIn(accountDto.getId()));
        return message;
    }

//    @RequestMapping(value = "/findTotalSignIn", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseMessageDto findTotalSignIn(HttpSession session) {
//        ResponseMessageDto message = new ResponseMessageDto();
//        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
//        message.setData(scoreRecordService.findTotalSignIn(accountDto.getId()));
//        message.setState(ResponseStateEnum.OK);
//        message.setMsg("查询成功");
//        return message;
//    }

    @RequestMapping(value = "/convertAward", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto convertAward(AwardTypeEnum awardType, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.updateConvertAward(accountDto.getId(), awardType);
        message.setState(ResponseStateEnum.OK);
        message.setData(scoreRecordService.findAllAwards(accountDto.getId()));
        message.setMsg("兑换成功");
        return message;
    }

    @RequestMapping(value = "/findMyAwards", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findMyAwards(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setData(scoreRecordService.findMyAward(accountDto.getId()));
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/findAllAwards", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findAllAwards(HttpSession session) {

        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        List<AwardDto> awardDtos = scoreRecordService.findAllAwards(accountDto.getId());
        message.setData(awardDtos);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

    /**
     * 查找剩余积分
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/findResidueScore", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findResidueScore(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        TotalScoreDto totalScoreDto = scoreRecordService.findTotalScore(accountDto.getId());
        if (totalScoreDto != null) {
            totalScoreDto.setNickName(accountDto.getNickName());
            totalScoreDto.setPortrait(accountDto.getPortrait());
        }
        message.setData(totalScoreDto);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

    @RequestMapping(value = "/findTopAward", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findTopAward(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        String codeSecret = scoreRecordService.updateTop20AwardByAccountId(accountDto.getId());
        message.setData(codeSecret);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

    @RequestMapping(value = "/findLuckyAward", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findLuckyAward(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        String codeSecret = scoreRecordService.updateLucky20AwardByAccountId(accountDto.getId());
        message.setData(codeSecret);
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/findSelfRank", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findSelfRank(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        message.setData(scoreRecordService.findSelfRank(accountDto.getId()));
        return message;
    }

    @RequestMapping(value = "/findIsTop20", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findIsTop20Yesterday(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setData(scoreRecordService.findIsTop20(accountDto.getId()));
        message.setMsg("查询成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/runTask", method = RequestMethod.GET)
    @ResponseBody
    public void runTask(HttpSession session) {
        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println("*****************"+dateString);
        timerTaskService.updateRunTask();
    }


}
