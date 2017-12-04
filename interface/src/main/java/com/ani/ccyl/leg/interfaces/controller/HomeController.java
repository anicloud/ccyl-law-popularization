package com.ani.ccyl.leg.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public String index() {
        return "forward:/WEB-INF/index.jsp";
    }
}
