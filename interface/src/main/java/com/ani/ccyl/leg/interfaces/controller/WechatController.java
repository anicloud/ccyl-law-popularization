package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import com.ani.ccyl.leg.commons.utils.ParseXmlUtil;
import com.ani.ccyl.leg.commons.utils.SignUtil;
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
    @RequestMapping(value = "/")
    @ResponseBody
    public void helloWorld(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!StringUtils.isEmpty(echostr) && SignUtil.checkSignature(signature, timestamp, nonce)) {
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
            String xml = sb.toString();

            ReceiveXmlEntity msgEntity = ParseXmlUtil.getMsgEntity(xml);
            System.out.print(msgEntity);
        }
    }
}
