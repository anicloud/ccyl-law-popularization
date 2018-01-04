package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ScoreRecordService scoreRecordService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    private String appId = Constants.PROPERTIES.getProperty("wechat.appid");

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
    public ModelAndView toThumbUp(Integer accountId) throws IOException {
//        String url = Constants.PROPERTIES.getProperty("wechat.entrance.url").replace("APPID",appId).replace("REDIRECT_URI",Constants.PROPERTIES.getProperty("wechat.redirect.url")).replace("STATE",accountId);
//        response.sendRedirect(url);
        ModelAndView modelAndView = new ModelAndView("");
        AccountDto toAccountDto = accountService.findById(accountId);
        if(toAccountDto != null) {
            ThumbUpDto thumbUpDto = new ThumbUpDto();
            thumbUpDto.setToNickName(toAccountDto.getNickName());
            thumbUpDto.setToPortrait(toAccountDto.getPortrait());
            TotalScoreDto totalScore = scoreRecordService.findTotalScore(accountId);
            thumbUpDto.setTotalScore(totalScore == null?0:totalScore.getScore());
            thumbUpDto.setAccountId(accountId);
            modelAndView.addObject("thumbUpDto",thumbUpDto);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto thumbUp(Integer toAccountId) {
        ResponseMessageDto message = new ResponseMessageDto();
//        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
//        scoreRecordService.insertScore(toAccountId,Constants.Score.THUMB_UP_SCORE,null,ScoreSrcTypeEnum.THUMB_UP,accountDto.getId());
        Integer thumbUpCount = scoreRecordMapper.findDailyThumbUpCount(toAccountId);
        if(thumbUpCount<5) {
            ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
            scoreRecordPO.setAccountId(toAccountId);
            scoreRecordPO.setSrcType(ScoreSrcTypeEnum.THUMB_UP);
            scoreRecordPO.setScore(Constants.Score.THUMB_UP_SCORE);
            scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            scoreRecordPO.setDel(false);
            scoreRecordMapper.insertSelective(scoreRecordPO);
            message.setMsg("点赞成功");
            message.setState(ResponseStateEnum.OK);
        } else {
            message.setMsg("点赞次数达到上限");
            message.setState(ResponseStateEnum.ERROR);
        }
        return message;
    }

    @RequestMapping(value = "goToAnswerQuestion")
    public String goToAnswerQuestion() {
        return "subscribe";
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
