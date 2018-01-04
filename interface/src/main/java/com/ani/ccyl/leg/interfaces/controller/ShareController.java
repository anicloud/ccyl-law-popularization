package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/share")
public class ShareController {
    private String appId = Constants.PROPERTIES.getProperty("wechat.appid");
    @Autowired
    private ScoreRecordService scoreRecordService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto share(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(accountDto.getId(),Constants.Score.SHARE_SCORE,null, ScoreSrcTypeEnum.SHARE,accountDto.getId());
        message.setMsg("分享成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/findShareInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findShareInfo(HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        HttpSession session = request.getSession();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        TotalScoreDto totalScore = scoreRecordService.findTotalScore(accountDto.getId());
        Integer correctCount = questionService.findDailyCorrectCount(accountDto.getId());
        ShareDto shareDto = new ShareDto();
        shareDto.setCorrectCount(correctCount);
        try {
            shareDto.setTotalScore(totalScore==null?0:totalScore.getScore());
        } catch (NullPointerException e) {
            shareDto.setTotalScore(0);
        }
        shareDto.setUrl(Constants.PROPERTIES.getProperty("http.host")+"/share/toThumbUp?accountId="+accountDto.getId());
        shareDto.setPortrait(accountDto.getPortrait());
        message.setMsg("查询成功");
        message.setData(shareDto);
        message.setState(ResponseStateEnum.OK);
        return message;
    }
    @RequestMapping(value = "/toThumbUp",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto toThumbUp(String accountId, HttpServletResponse response) throws IOException {
        ResponseMessageDto message = new ResponseMessageDto();
        String url = Constants.PROPERTIES.getProperty("wechat.entrance.url").replace("APPID",appId).replace("REDIRECT_URI",Constants.PROPERTIES.getProperty("wechat.redirect.url")).replace("STATE",accountId);
        response.sendRedirect(url);
        message.setMsg("success");
        message.setState(ResponseStateEnum.OK);
        return message;
    }
    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto thumbUp(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        scoreRecordService.insertScore(toAccountId,Constants.Score.THUMB_UP_SCORE,null,ScoreSrcTypeEnum.THUMB_UP,accountDto.getId());
        message.setMsg("点赞成功");
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/findThumbUpInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findThumbUpInfo(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        AccountDto toAccountDto = accountService.findById(toAccountId);
        if(toAccountDto != null) {
            ThumbUpDto thumbUpDto = new ThumbUpDto();
            thumbUpDto.setIsThumbUp(scoreRecordService.findIsThumbUp(accountDto.getId(),toAccountId));
            thumbUpDto.setToNickName(toAccountDto.getNickName());
            thumbUpDto.setToPortrait(toAccountDto.getPortrait());
            TotalScoreDto totalScore = scoreRecordService.findTotalScore(toAccountId);
            thumbUpDto.setTotalScore(totalScore == null?0:totalScore.getScore());
            message.setData(thumbUpDto);
        }
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
}
