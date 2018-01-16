package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.HttpMessageEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ScoreRecordService scoreRecordService;
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
    @RequestMapping("/pc")
    public ModelAndView pc() {
        ModelAndView modelAndView=new ModelAndView("computerHome");
        Map<String,Object> map=scoreRecordService.findTotalInfo();
        modelAndView.addObject("infoMap",map);
        return modelAndView;
//        return "computerHome";
    }



}
