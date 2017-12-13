package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import com.ani.ccyl.leg.commons.dto.wechat.TextMessage;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by lihui on 17-12-4.
 */
@Service
public class WechatServiceImpl implements WechatService {
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
                }
            } else if(Constants.WechatMsgType.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {//订阅类型的消息
                respContent = "中国共青团欢迎您的到来！";
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
}
