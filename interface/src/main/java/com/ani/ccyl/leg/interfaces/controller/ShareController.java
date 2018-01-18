package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.commons.utils.IPUtil;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import com.ani.ccyl.leg.service.service.facade.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    @Autowired
    private ShareRelationService shareRelationService;
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
        shareDto.setUrl(Constants.PROPERTIES.getProperty("http.host")+"/share/toThumbUp/"+accountDto.getId());
        shareDto.setPortrait(accountDto.getPortrait());
        message.setMsg("查询成功");
        message.setData(shareDto);
        message.setState(ResponseStateEnum.OK);
        return message;
    }

    @RequestMapping(value = "/toThumbUp/{accountId}",method = RequestMethod.GET)
    public void toThumbUp(@PathVariable Integer accountId, HttpServletResponse response) throws IOException {
        String url = Constants.PROPERTIES.getProperty("wechat.entrance.url").replace("APPID",appId).replace("REDIRECT_URI", URLEncoder.encode(Constants.PROPERTIES.getProperty("wechat.redirect.url"),"utf-8")).replace("STATE",accountId+"");
        response.sendRedirect(url);
    }

    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto thumbUp(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        if(toAccountId.equals(accountDto.getId())){
            message.setState(ResponseStateEnum.EXCEPTION);
            message.setMsg("对不起，不能为自己点赞哦");
            return message;
        }
        Boolean isThumbUp = scoreRecordService.findIsThumbUp(accountDto.getId(),toAccountId);
        if(!isThumbUp) {
            scoreRecordService.insertScore(toAccountId,Constants.Score.THUMB_UP_SCORE,null,ScoreSrcTypeEnum.THUMB_UP,accountDto.getId());
            message.setState(ResponseStateEnum.OK);
            message.setMsg("点赞成功");
        } else{
            message.setState(ResponseStateEnum.ERROR);
            message.setMsg("已经点过赞了");
        }

        return message;
    }

    @RequestMapping(value = "/findThumbUpInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findThumbUpInfo(Integer toAccountId, HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto toAccountDto = accountService.findById(toAccountId);
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        if(toAccountDto != null) {
            ThumbUpDto thumbUpDto = new ThumbUpDto();
            thumbUpDto.setIsThumbUp(scoreRecordService.findIsThumbUp(accountDto.getId(),toAccountId));
            thumbUpDto.setToNickName(toAccountDto.getNickName());
            thumbUpDto.setToPortrait(toAccountDto.getPortrait());
            thumbUpDto.setThumbUpCount(scoreRecordMapper.findThumbUpCount(toAccountId));
            TotalScoreDto totalScore = scoreRecordService.findTotalScore(toAccountId);
            int score=0;
            if (totalScore != null && totalScore.getScore()!=null){
                score=totalScore.getScore();
            }
            thumbUpDto.setTotalScore(score);
            message.setData(thumbUpDto);
        }
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
    @RequestMapping(value = "/findInviteInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findInviteInfo(HttpSession session) throws UnsupportedEncodingException {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        List<InvitedDto> invitedDtos=shareRelationService.selectByShareId(accountDto.getId());
        message.setData(invitedDtos);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

    @RequestMapping(value = "/findThumbListInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findThumbListInfo(HttpSession session) throws UnsupportedEncodingException {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("accountId",accountDto.getId());
        paramMap.put("srcType",2);
        List<InvitedDto> invitedDtos=scoreRecordMapper.selectByAccountId(paramMap);
        for(InvitedDto invitedDto : invitedDtos){
            if(invitedDto.getNickName()!=null){
                invitedDto.setNickName(URLDecoder.decode(invitedDto.getNickName(), "utf-8"));
            }
        }
        message.setData(invitedDtos);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
}
