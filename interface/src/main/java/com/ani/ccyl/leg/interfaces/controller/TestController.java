package com.ani.ccyl.leg.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

/**
 * Created by lihui on 17-12-1.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/helloWorld")
    @ResponseBody
    public String helloWorld() throws IOException {
        return "helloWorld";
    }
}
