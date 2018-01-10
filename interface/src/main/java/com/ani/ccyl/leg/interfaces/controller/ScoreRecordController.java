package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.TotalScoreMapper;
import com.ani.ccyl.leg.persistence.po.DailyAwardsPO;
import com.ani.ccyl.leg.persistence.po.UpdateScorePO;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    private QuestionService questionService;
    @Autowired
    private TotalScoreMapper totalScoreMapper;
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
    public ResponseMessageDto findTotalScore(HttpSession session) throws UnsupportedEncodingException {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        TotalScoreDto totalScoreDto = scoreRecordService.findTotalScore(accountDto.getId());
        if(totalScoreDto != null) {
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
        scoreRecordService.insertScore(accountDto.getId(),Constants.Score.SIGN_IN_SCORE,null, ScoreSrcTypeEnum.SIGN_IN,accountDto.getId());
        TotalScoreDto totalScore = scoreRecordService.findTotalScore(accountDto.getId());
        if(totalScore != null) {
            totalScore.setPortrait(accountDto.getPortrait());
            totalScore.setNickName(accountDto.getNickName());
        }
        Integer lastScore = totalScore.getScore();
        List<MyAwardDto> myConvertAwards = scoreRecordService.findMyConvertAward(accountDto.getId());

        if(myConvertAwards!=null) {
            for(MyAwardDto myAwardsPO:myConvertAwards) {
                lastScore = lastScore - myAwardsPO.getAwardType().findScore();
            }
        }
        /**减去清空积分表中的清空积分**/
        UpdateScorePO updateScorePO = totalScoreMapper.selectByPrimaryKey(accountDto.getId());
        if(updateScorePO!=null){
            lastScore = lastScore - updateScorePO.getDeleteScore();
        }
        /**减去清空积分表中的清空积分**/
        totalScore.setScore(lastScore);//剩余积分
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
        message.setState(ResponseStateEnum.OK);
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
     * @param session
     * @return
     */
    @RequestMapping(value = "/findResidueScore", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findResidueScore(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        /*使用TatalScoreDto里的分数来代表剩余积分*/
        TotalScoreDto totalScoreDto = scoreRecordService.findTotalScore(accountDto.getId());
        Integer lastScore = totalScoreDto.getScore();
        List<MyAwardDto> myConvertAwards = scoreRecordService.findMyConvertAward(accountDto.getId());

        if(myConvertAwards!=null) {
            for(MyAwardDto myAwardsPO:myConvertAwards) {
                lastScore = lastScore - myAwardsPO.getAwardType().findScore();
            }
        }
        /**减去清空积分表中的清空积分**/
        UpdateScorePO updateScorePO = totalScoreMapper.selectByPrimaryKey(accountDto.getId());
        if(updateScorePO!=null){
            lastScore = lastScore - updateScorePO.getDeleteScore();
        }
        /**减去清空积分表中的清空积分**/
        totalScoreDto.setScore(lastScore);//剩余积分
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
}
