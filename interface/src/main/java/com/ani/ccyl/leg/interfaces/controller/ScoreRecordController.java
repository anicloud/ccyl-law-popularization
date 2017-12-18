package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihui on 17-12-18.
 */
@Controller
@RequestMapping("/score")
public class ScoreRecordController {
    @Autowired
    private ScoreRecordService scoreRecordService;
    @RequestMapping("/findDailyRecord")
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
}
