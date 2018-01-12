package com.ani.ccyl.leg.interfaces.controller;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.HttpMessageEnum;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import com.ani.ccyl.leg.service.service.facade.ShareRelationService;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
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
    @Autowired
    private ShareRelationService shareRelationService;
    @Autowired
    private ScoreRecordService scoreRecordService;
    @RequestMapping(value = "/entrance")
    @ResponseBody
    public void entrance(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        if (!StringUtils.isEmpty(echostr) && WechatUtil.checkSignature(signature, timestamp, nonce)) {
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
            writer.close();
        } else {
            String respXml = wechatService.processRequest(request, response);
            ServletOutputStream outputStream = response.getOutputStream();
            if (respXml!=null)
                outputStream.write(respXml.getBytes("UTF-8"));
            outputStream.close();
        }
    }

    @RequestMapping("/shareUrl")
    @ResponseBody
    public ResponseMessageDto getShareUrl(HttpSession session) {
        ResponseMessageDto message = new ResponseMessageDto();
        AccountDto accountDto = (AccountDto) session.getAttribute(Constants.LOGIN_SESSION);
        String url = Constants.PROPERTIES.getProperty("wechat.entrance.url").replace("APPID",appId).replace("REDIRECT_URI",Constants.PROPERTIES.getProperty("wechat.redirect.url")).replace("STATE",String.valueOf(accountDto.getId()));
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        message.setData(url);
        return message;
    }

    @RequestMapping(value = "/makeMenu")
    @ResponseBody
    public ResponseMessageDto makeMenu() throws IOException {
        ResponseMessageDto message = new ResponseMessageDto();
        AccessTokenDto accessToken = wechatService.updateToken();

        if (null != accessToken) {
            int result = WechatUtil.createMenu(WechatUtil.getMenu(), accessToken.getAccessToken());
            if (0 == result){
                message.setMsg("菜单创建成功");
                message.setState(ResponseStateEnum.OK);
            }else{
                message.setState(ResponseStateEnum.ERROR);
                message.setMsg("菜单创建失败");
            }
        }
        return message;
    }

    @RequestMapping("/redirectNew")//{"openid":"orf6ew_iTOFmBrXb9bG5b-IY2TeI","nickname":"狂奔的蜗牛","sex":1,"language":"zh_CN","city":"石家庄","province":"河北","country":"中国","headimgurl":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKicVRn27Eo1qZJbicicMVIEIaVicIibic4ic111n0H6lzsicqIoJiaqHpy8cn6Go483ZiaczuVPSumFgIBeYUw/132","privilege":[]}
    public void redirectNew(Integer toAccountId, String srcAccountJson, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        srcAccountJson = "{\"openid\":\"orf6ew_iTOFmBrXb9bG5b-IY2TeI\",\"nickname\":\"狂奔的蜗牛\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"石家庄\",\"province\":\"河北\",\"country\":\"中国\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKicVRn27Eo1qZJbicicMVIEIaVicIibic4ic111n0H6lzsicqIoJiaqHpy8cn6Go483ZiaczuVPSumFgIBeYUw/132\",\"privilege\":[]}";
        if(!StringUtils.isEmpty(srcAccountJson)) {
            AccountDto accountDto = accountService.insertAccount(JSONObject.fromObject(srcAccountJson));
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(accountDto.getOpenId(), accountDto.getAccountPwd());
            subject.login(token);
            AccountDto loginAccount = (AccountDto) subject.getPrincipal();
            session.setAttribute(Constants.LOGIN_SESSION,loginAccount);
            Cookie cookie = new Cookie(Constants.LOGIN_COOKIE, String.valueOf(loginAccount.getId()));
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            if(accountDto.getNew() && toAccountId != null) {
                AccountDto toAccount = accountService.findById(toAccountId);
                shareRelationService.insert(toAccount.getId(),loginAccount.getId(),false);
                response.sendRedirect(request.getContextPath()+"/home/index?op="+ HttpMessageEnum.THUMB_UP.name()+"&id="+toAccount.getId());
            } else
                response.sendRedirect(request.getContextPath()+"/home/index?op="+ HttpMessageEnum.LOGIN_SUCCESS.name());
        } else {
            response.sendRedirect(request.getContextPath()+"/home/index?op="+HttpMessageEnum.LOGIN_FAILURE.name());
        }
    }

    @RequestMapping("/redirect")
    public void redirect(String code, String state, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        String tokenUrl = oauthTokenUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject tokenObj = WechatUtil.httpRequest(tokenUrl, "GET", null);
        HttpSession session = request.getSession();
        if(tokenObj!=null) {
            if(tokenObj.containsKey("access_token")) {
                String accessToken = tokenObj.getString("access_token");
                String openId = tokenObj.getString("openid");
                String userInfoUrl = fetchUserInfoUrl.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openId);
                JSONObject userObj = WechatUtil.httpRequest(userInfoUrl,"GET",null);
                String userInfoJson = userObj.toString();
                System.out.print(userInfoJson);
                AccountDto accountDto = accountService.insertAccount(userObj);
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(accountDto.getOpenId(), accountDto.getAccountPwd());
                subject.login(token);
                AccountDto loginAccount = (AccountDto) subject.getPrincipal();
                session.setAttribute(Constants.LOGIN_SESSION,loginAccount);
                Cookie cookie = new Cookie(Constants.LOGIN_COOKIE, String.valueOf(loginAccount.getId()));
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                if(accountDto.getNew() && state.matches("^[0-9]+$")) {
                    AccountDto toAccount = accountService.findById(Integer.parseInt(state));
                    shareRelationService.insert(toAccount.getId(),loginAccount.getId(),false);
                    response.sendRedirect(request.getContextPath()+"/home/index?op="+ HttpMessageEnum.THUMB_UP.name()+"&id="+toAccount.getId());
                } else
                    response.sendRedirect(request.getContextPath()+"/home/index?op="+ HttpMessageEnum.LOGIN_SUCCESS.name());
            } else if(tokenObj.containsKey("errcode")) {
                response.sendRedirect(request.getContextPath()+"/home/index?op="+HttpMessageEnum.LOGIN_FAILURE.name());
            }
        }
    }

    @RequestMapping(value = "/getJsSDKConfig",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessageDto getJsSDKConfig(String timestamp, String nonceStr, String url, HttpServletRequest request) throws IOException {
        ResponseMessageDto message = new ResponseMessageDto();
//        AccessTokenDto accessToken = wechatService.updateToken();
//
//        if(accessToken != null) {
//            String jsSDKSign = WechatUtil.getJsSDKSign(nonceStr, accessToken.getJsapiTicket(), timestamp, url);
//            JsSDKConfigDto jsSDKConfigDto = new JsSDKConfigDto();
//            jsSDKConfigDto.setAppId(appId);
//            jsSDKConfigDto.setNonceStr(nonceStr);
//            jsSDKConfigDto.setSignature(jsSDKSign);
//            jsSDKConfigDto.setTimestamp(timestamp);
//            message.setData(jsSDKConfigDto);
//        }
        String ccylUrl = Constants.PROPERTIES.getProperty("ccyl.url");
        ccylUrl = ccylUrl.replace("TIME",timestamp).replace("STRING",nonceStr).replace("URL",url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(ccylUrl);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null) {
            message.setData(EntityUtils.toString(entity));
        }
        message.setState(ResponseStateEnum.OK);
        message.setMsg("查询成功");
        return message;
    }

}
