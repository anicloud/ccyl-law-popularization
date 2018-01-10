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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
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
        shareDto.setUrl(Constants.PROPERTIES.getProperty("http.host")+"/share/toThumbUp?accountId="+accountDto.getId());
        shareDto.setPortrait(accountDto.getPortrait());
        message.setMsg("查询成功");
        message.setData(shareDto);
        message.setState(ResponseStateEnum.OK);
        return message;
    }
    @RequestMapping(value = "/toThumbUp",method = RequestMethod.GET)
    public ModelAndView toThumbUp(Integer accountId,HttpServletRequest request) throws IOException {
        String uniCodeString = getUniCodeFromRequest(request);
      //  uniCodeString="";
        AccountDto toAccountDto = accountService.findById(accountId);
        ModelAndView modelAndView =null;
        boolean isThumbUped=false;

        if (!StringUtils.isEmpty(uniCodeString)){
            isThumbUped = scoreRecordService.findIsThumbUp(Long.parseLong(uniCodeString),accountId);
        }
        if(!isThumbUped){
             modelAndView = new ModelAndView("thumbUp");
            if(toAccountDto != null) {
                ThumbUpDto thumbUpDto = new ThumbUpDto();
                thumbUpDto.setToNickName(toAccountDto.getNickName());
                thumbUpDto.setToPortrait(toAccountDto.getPortrait());
                TotalScoreDto totalScore = scoreRecordService.findTotalScore(accountId);
                thumbUpDto.setTotalScore(totalScore == null?0:totalScore.getScore());
                thumbUpDto.setAccountId(accountId);
                modelAndView.addObject("thumbUpDto",thumbUpDto);
            }

        }
        else {
            if (toAccountDto != null){
                modelAndView = new ModelAndView("answerQuestion");
                modelAndView.addObject("isThumbUp",isThumbUped);
                modelAndView.addObject("nickName",toAccountDto.getNickName());
            }

        }

        return modelAndView;
    }
    private String getUniCodeFromRequest(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if(cook.getName().equalsIgnoreCase("uniCode")){ //获取键
                String uniCodeString = cook.getValue().toString();
                System.out.println("account:"+cook.getValue().toString());    //获取值
                return uniCodeString;

            }
        }
        return "";

    }
    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    public ModelAndView thumbUp(Integer toAccountId, HttpServletRequest request) {
        String uniCodeString = getUniCodeFromRequest(request);
        AccountDto accountDto = accountService.findById(toAccountId);
        boolean isThumbUped=false;
        ModelAndView modelAndView = new ModelAndView("answerQuestion");
       // uniCodeString="";
        if (!StringUtils.isEmpty(uniCodeString)){
            isThumbUped = scoreRecordService.findIsThumbUp(Long.parseLong(uniCodeString),toAccountId);
        }
        if (isThumbUped){
            modelAndView.addObject("nickName",accountDto.getNickName());
            modelAndView.addObject("isThumbUp",isThumbUped);

        }else {

            // AccountDto accountDto = accountService.findById(toAccountId);
            Long uniCode =  generateUniCode();
            ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
            scoreRecordPO.setUniCode(uniCode);
            scoreRecordPO.setAccountId(toAccountId);
            scoreRecordPO.setSrcType(ScoreSrcTypeEnum.THUMB_UP);
            scoreRecordPO.setScore(Constants.Score.THUMB_UP_SCORE);
            scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            scoreRecordPO.setDel(false);
            scoreRecordMapper.insertSelective(scoreRecordPO);
            modelAndView.addObject("nickName",accountDto.getNickName());
            modelAndView.addObject("isThumbUp",isThumbUped);
            modelAndView.addObject("uniCode",uniCode+"");

        }





        return modelAndView;
    }
    private Long generateUniCode(){
        Random random = new Random(System.currentTimeMillis() + (new Random()).nextLong());
        return Long.valueOf(Math.abs(random.nextLong()));

    }
    @RequestMapping(value = "/goToAnswerQuestion")
    public String goToAnswerQuestion() {
        return "subscribe";
    }

    @RequestMapping(value = "/findThumbUpInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findThumbUpInfo(Integer toAccountId, HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto toAccountDto = accountService.findById(toAccountId);
        String macAddress = IPUtil.getMACAddress(IPUtil.getRemoteAddress(request));
        if(toAccountDto != null) {
            ThumbUpDto thumbUpDto = new ThumbUpDto();
            thumbUpDto.setIsThumbUp(scoreRecordService.findIsThumbUp(macAddress,toAccountId));
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
    @RequestMapping(value = "/findInviteInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto findInviteInfo(Integer accountId){
        ResponseMessageDto message = new ResponseMessageDto();
        List<InvitedDto> invitedDtos=shareRelationService.selectByShareId(accountId);
       // message.s
        message.setData(invitedDtos);
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }
}
