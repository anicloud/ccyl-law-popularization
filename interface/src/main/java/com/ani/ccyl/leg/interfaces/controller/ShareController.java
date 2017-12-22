package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lihui on 17-12-21.
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    @RequestMapping("/share")
    @ResponseBody
    public void share(String toAccountId, HttpServletResponse response) throws IOException {
        String url = Constants.PROPERTIES.getProperty("wechat.entrance.url").replace("APPID",Constants.PROPERTIES.getProperty("wechat.appid")).replace("REDIRECT_URI",Constants.PROPERTIES.getProperty("wechat.redirect.url")).replace("STATE",String.valueOf(toAccountId));
        response.sendRedirect(url);
    }
}
