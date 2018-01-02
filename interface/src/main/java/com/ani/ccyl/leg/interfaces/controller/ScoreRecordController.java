package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        DailyTotalScoreDto scoreDto = scoreRecordService.findDailyTotalScore(id == null?accountDto.getId():id,ScoreSrcTypeEnum.QUESTION);
        message.setState(ResponseStateEnum.OK);
        message.setData(scoreDto);
        message.setMsg("查询成功");
        return message;
    }

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
        scoreRecordService.insertScore(accountDto.getId(),Constants.Score.SIGN_IN_SCORE,null, ScoreSrcTypeEnum.SIGN_IN,accountDto.getId());
        message.setState(ResponseStateEnum.OK);
        message.setMsg("签到成功");
        return message;
    }

    @RequestMapping(value = "thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto thumbUp(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(toAccountId,Constants.Score.THUMB_UP_SCORE,null,ScoreSrcTypeEnum.THUMB_UP,accountDto.getId());
        message.setMsg("点赞成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "share", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto share(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(accountDto.getId(),Constants.Score.SHARE_SCORE,null,ScoreSrcTypeEnum.SHARE,accountDto.getId());
        message.setMsg("分享成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/isThumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto isThumbUp(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        Boolean isThumbUp = scoreRecordService.findIsThumbUp(accountDto.getId(),toAccountId);
        message.setData(isThumbUp);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
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

    @RequestMapping(value = "/findTotalSignIn", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findTotalSignIn(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        message.setData(scoreRecordService.findTotalSignIn(accountDto.getId()));
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
    @RequestMapping(value = "/convertAward", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto convertAward(AwardTypeEnum awardType, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.updateConvertAward(accountDto.getId(), awardType);
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
    @RequestMapping(value = "/findTopOrLuckyAward", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findTopOrLuckyAward(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        String codeSecret = scoreRecordService.findTop20LuckyAward(accountDto.getId());
        message.setData(codeSecret);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
}
