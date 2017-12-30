package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.dto.AccessTokenDto;
import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;
import com.ani.ccyl.leg.commons.utils.WechatUtil;
import com.ani.ccyl.leg.service.service.facade.WechatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lihui on 17-12-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class TestWechat {
    @Autowired
    private WechatService wechatService;
    @Test
    public void testParseXml() throws Exception {
        String xml = "<xml>    <URL><![CDATA[http://84799e25.ngrok.io/leg/test/helloWorld]]></URL>    <ToUserName><![CDATA[lh812486664]]></ToUserName>    <FromUserName><![CDATA[test]]></FromUserName>    <CreateTime>1</CreateTime>    <MsgType><![CDATA[text]]></MsgType>    <Content><![CDATA[testmsg]]></Content>    <MsgId>123</MsgId>    <Encrypt><![CDATA[5i7mxHYEmuQqQvZtXhlobdKgZFnWrv62K0MPDlnUZfkJUY+SGtDFtYXfkWFppHxiIz3uEwvP7If98gWyqgYa7qG6MBKd+ftzXGJq92uJSg9ZM9BgrFw5259ZOa68eWgHH9Imko05eqhVB7NZBlFqb1QWcD9Jp0r4jf9SdZdbVplO7XaZrEqhXOSC3yfz4hJWd6Q124qWXQdaLQNwt6ta2Y/BsGuwb9g7Go9Eht/Rt2GR+RQFJa3PG84XNGiwA0Xflgzl6JPy8efgbFjDvf8er0oiRJbxWt5mSTtu1DuCesS4YiFJVXUXqGLVHvx57NqbwP5wvRFZMHT5FK+8y7VR4BjuBwnpaXQtao+Fw08F3qvoVjvvxjRY4LzUowy4PXuYH3eUoTuuTFtx0JBmUEHacHcYoRaG67gyxOhrFAMzA1O9poczja0sMj277uaFOdJSXQNWVItqH35bm0GrE1GP8g==]]></Encrypt></xml>";
        ReceiveXmlEntity msgEntity = WechatUtil.getMsgEntity(xml);
        System.out.print(msgEntity);
    }

    @Test
    public void testMakeMenu() {
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa8a719b165e1d3dc&redirect_uri=https://sandbox.bj.anicel.cn/leg/wechat/redirect&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
        AccessTokenDto accessToken = wechatService.updateToken();
        WechatUtil.createMenu(WechatUtil.getMenu(),accessToken.getAccessToken());
        int result = WechatUtil.createMenu(WechatUtil.getMenu(), accessToken.getAccessToken());
        if (0 == result){
            System.out.print("菜单创建成功");
        }else{
            System.out.print("菜单创建失败");
        }
    }
}
