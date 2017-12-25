package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccessTokenDto;
import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import com.ani.ccyl.leg.commons.dto.wechat.TextMessage;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.persistence.mapper.AccessTokenMapper;
import com.ani.ccyl.leg.persistence.po.AccessTokenPO;
import com.ani.ccyl.leg.service.adapter.TokenAdapter;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by lihui on 17-12-4.
 */
@Service
public class WechatServiceImpl implements WechatService {
    private String appId = Constants.PROPERTIES.getProperty("wechat.appid");
    private String appSecret = Constants.PROPERTIES.getProperty("wechat.appsecret");
    private String ticketUrl = Constants.PROPERTIES.getProperty("wechat.jsapi.ticket.url");
    @Autowired
    private AccessTokenMapper accessTokenMapper;
    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) {
        String respContent = "请求处理异常，请稍后尝试！";
        ReceiveXmlEntity msgEntity = WechatUtil.getMsgEntity(request);
        if(msgEntity != null) {
            String fromUserName = msgEntity.getFromUserName();
            String toUserName = msgEntity.getToUserName();
            String msgType = msgEntity.getMsgType();
            String fromContent = msgEntity.getContent();
            String userName = "";
            String eventType = msgEntity.getEvent();
            if(Constants.WechatMsgType.REQ_MESSSAGE_TYPE_EVENT.equals(msgType)) {//事件类型的消息
                if(eventType.equals(Constants.WechatMsgType.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应，然后分别处理
                    String eventKey = msgEntity.getEventKey();
                    if("suggestions".equals(eventKey)) {//意见反馈

                    }
                } else if(Constants.WechatMsgType.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {//订阅类型的消息
                    respContent = "中国共青团欢迎您的到来！";
                }
            }
            if(fromContent!=null && fromContent.contains("用户名绑定")) {
                //绑定微信用户到后台账户
                respContent = "用户绑定";

            }
            //回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(Constants.WechatMsgType.RESP_MESSSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            textMessage.setContent(respContent);
            return WechatUtil.textMessageToXml(textMessage);
        }
        return null;
    }

    @Override
    public AccessTokenDto updateToken() {
        AccessTokenPO accessTokenParam = new AccessTokenPO();
        accessTokenParam.setDel(false);
        List<AccessTokenPO> tokenPOs = accessTokenMapper.select(accessTokenParam);
        AccessTokenDto accessToken;
        if(tokenPOs.size() == 0) {
            accessToken = WechatUtil.getAccessToken(appId,appSecret);
            String newTicketUrl = ticketUrl.replace("ACCESS_TOKEN",accessToken.getAccessToken());
            JSONObject ticketResult = WechatUtil.httpRequest(newTicketUrl, "GET", null);
            if("0".equals(ticketResult.getString("errcode"))) {
                accessToken.setJsapiTicket(ticketResult.getString("ticket"));
                accessToken.setTicketCreateTime(new Timestamp(System.currentTimeMillis()));
                accessToken.setTicketExpiresIn(ticketResult.getLong("expires_in"));
            }
            AccessTokenPO accessTokenPO = TokenAdapter.fromDto(accessToken);
            accessTokenMapper.insertSelective(accessTokenPO);
            accessToken.setId(accessTokenPO.getId());
        } else {
            AccessTokenPO accessTokenPO = tokenPOs.get(0);
            if((System.currentTimeMillis()-accessTokenPO.getTokenCreateTime().getTime())>=accessTokenPO.getTokenExpiresIn()*1000) {
                accessToken = WechatUtil.getAccessToken(appId,appSecret);
                String newTicketUrl = ticketUrl.replace("ACCESS_TOKEN",accessToken.getAccessToken());
                JSONObject ticketResult = WechatUtil.httpRequest(newTicketUrl, "GET", null);
                accessTokenPO.setTokenCreateTime(new Timestamp(System.currentTimeMillis()));
                accessTokenPO.setAccessToken(accessToken.getAccessToken());
                accessTokenPO.setTokenExpiresIn(accessToken.getTokenExpiresIn());
                if("0".equals(ticketResult.getString("errcode"))) {
                    accessTokenPO.setJsapiTicket(ticketResult.getString("ticket"));
                    accessTokenPO.setTicketCreateTime(new Timestamp(System.currentTimeMillis()));
                    accessTokenPO.setTicketExpiresIn(ticketResult.getLong("expires_in"));
                }
            }
            if(StringUtils.isEmpty(accessTokenPO.getJsapiTicket()) ||
                    (System.currentTimeMillis()-accessTokenPO.getTicketCreateTime().getTime())>=accessTokenPO.getTicketExpiresIn()*1000) {
                String newTicketUrl = ticketUrl.replace("ACCESS_TOKEN", accessTokenPO.getAccessToken());
                JSONObject ticketResult = WechatUtil.httpRequest(newTicketUrl, "GET", null);
                if("0".equals(ticketResult.getString("errcode"))) {
                    accessTokenPO.setTicketExpiresIn(ticketResult.getLong("expires_in"));
                    accessTokenPO.setTicketCreateTime(new Timestamp(System.currentTimeMillis()));
                    accessTokenPO.setJsapiTicket(ticketResult.getString("ticket"));
                }
            }
            accessTokenMapper.updateByPrimaryKey(accessTokenPO);
            accessToken = TokenAdapter.fromPO(accessTokenPO);
        }
        return accessToken;
    }
}
