package com.ani.ccyl.leg.interfaces.controller;


import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String pc(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=scoreRecordService.findTotalInfo();
        request.setAttribute("infoMap",map);
        return "computerHome";
    }
    @RequestMapping("/zqw")
    public String aqwHome(HttpServletRequest request) {
        Map<String,Object> map=scoreRecordService.findTotalInfo();
        request.setAttribute("infoMap",map);
        return "zqwHome";
    }



}
