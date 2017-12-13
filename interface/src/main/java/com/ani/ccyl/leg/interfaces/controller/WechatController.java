package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccessTokenDto;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.JsSDKConfigDto;
import com.ani.ccyl.leg.commons.dto.ResponseMessageDto;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by lihui on 17-12-3.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    private String appId = Constants.PROPERTIES.getProperty("wechat.appid");
    private String appSecret = Constants.PROPERTIES.getProperty("wechat.appsecret");
    private String oauthTokenUrl = Constants.PROPERTIES.getProperty("wechat.access.oauth.token.url");
    private String fetchUserInfoUrl = Constants.PROPERTIES.getProperty("wechat.fetch.user.info.url");
    @Autowired
    private WechatService wechatService;
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/entrance")
    @ResponseBody
    public void entrance(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        if (!StringUtils.isEmpty(echostr) && WechatUtil.checkSignature(signature, timestamp, nonce)) {
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
        } else {
            String respXml = wechatService.processRequest(request, response);
            response.getOutputStream().write(respXml.getBytes("UTF-8"));
        }
    }

    @RequestMapping(value = "/makeMenu")
    @ResponseBody
    public void makeMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccessTokenDto accessToken = wechatService.updateToken();

        if (null != accessToken) {
            int result = WechatUtil.createMenu(WechatUtil.getMenu(), accessToken.getAccessToken());
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

    @RequestMapping("/redirect")
    public String redirect(String code, String state, HttpServletRequest request, HttpServletResponse response)  {
        String tokenUrl = oauthTokenUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject tokenObj = WechatUtil.httpRequest(tokenUrl, "GET", null);
        HttpSession session = request.getSession();
        if(tokenObj!=null) {
            if(tokenObj.containsKey("access_token")) {
                String accessToken = tokenObj.getString("access_token");
                String openId = tokenObj.getString("openid");
                String userInfoUrl = fetchUserInfoUrl.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openId);
                JSONObject userObj = WechatUtil.httpRequest(userInfoUrl,"GET",null);
                AccountDto accountDto = accountService.insertAccount(userObj);
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(accountDto.getOpenId(), accountDto.getAccountPwd());
                subject.login(token);
                AccountDto loginAccount = (AccountDto) subject.getPrincipal();
                session.setAttribute(Constants.LOGIN_SESSION,loginAccount);
            } else if(tokenObj.containsKey("errcode")) {
                return null;
            }
        }
        return "index";
    }

    @RequestMapping("/getJsSDKConfig")
    @ResponseBody
    public ResponseMessageDto getJsSDKConfig(String timestamp, String nonceStr, String url, HttpServletRequest request) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccessTokenDto accessToken = wechatService.updateToken();

        if(accessToken != null) {
            String jsSDKSign = WechatUtil.getJsSDKSign(nonceStr, accessToken.getJsapiTicket(), timestamp, url);
            JsSDKConfigDto jsSDKConfigDto = new JsSDKConfigDto();
            jsSDKConfigDto.setAppId(appId);
            jsSDKConfigDto.setNonceStr(nonceStr);
            jsSDKConfigDto.setSignature(jsSDKSign);
            jsSDKConfigDto.setTimestamp(timestamp);
            message.setData(jsSDKConfigDto);
        }
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

}
