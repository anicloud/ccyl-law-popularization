package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.wechat.AccessToken;
import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by lihui on 17-12-3.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    private String appId = Constants.PROPERTIES.getProperty("wechat.appid");
    private String appSecret = Constants.PROPERTIES.getProperty("wechat.appsecret");
    @Autowired
    private WechatService wechatService;
    @RequestMapping(value = "/entrance")
    @ResponseBody
    public void entrance(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        if (!StringUtils.isEmpty(echostr) && WechatUtil.checkSignature(signature, timestamp, nonce)) {
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
        } else {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            String respXml = wechatService.processRequest(sb.toString());
            response.getOutputStream().write(respXml.getBytes("UTF-8"));
        }
    }

    @RequestMapping(value = "/makeMenu")
    @ResponseBody
    public void makeMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccessToken accessToken = WechatUtil.getAccessToken(appId, appSecret);

        if (null != accessToken) {
            // 调用接口创建菜单
            int result = WechatUtil.createMenu(WechatUtil.getMenu(), accessToken.getToken());

            // 判断菜单创建结果
            if (0 == result){
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("菜单创建成功！");
                pw.flush();
            }else{
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.println("菜单创建失败，错误码：" + result);
                pw.flush();
            }
        }
    }

}
