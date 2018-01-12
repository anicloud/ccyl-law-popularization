package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.enums.HttpMessageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
    @RequestMapping("/pc")
    public String pc() {
        return "computerHome";
    }
}
