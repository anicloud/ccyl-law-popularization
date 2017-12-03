package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by lihui on 17-12-2.
 */
public class ParseXmlUtil {
    public static ReceiveXmlEntity getMsgEntity(String strXml) throws Exception {
        try {
            ReceiveXmlEntity msg = null;
            //<xml>    <URL><![CDATA[http://84799e25.ngrok.io/leg/test/helloWorld]]></URL>    <ToUserName><![CDATA[lh812486664]]></ToUserName>    <FromUserName><![CDATA[test]]></FromUserName>    <CreateTime>1</CreateTime>    <MsgType><![CDATA[text]]></MsgType>    <Content><![CDATA[testmsg]]></Content>    <MsgId>123</MsgId>    <Encrypt><![CDATA[5i7mxHYEmuQqQvZtXhlobdKgZFnWrv62K0MPDlnUZfkJUY+SGtDFtYXfkWFppHxiIz3uEwvP7If98gWyqgYa7qG6MBKd+ftzXGJq92uJSg9ZM9BgrFw5259ZOa68eWgHH9Imko05eqhVB7NZBlFqb1QWcD9Jp0r4jf9SdZdbVplO7XaZrEqhXOSC3yfz4hJWd6Q124qWXQdaLQNwt6ta2Y/BsGuwb9g7Go9Eht/Rt2GR+RQFJa3PG84XNGiwA0Xflgzl6JPy8efgbFjDvf8er0oiRJbxWt5mSTtu1DuCesS4YiFJVXUXqGLVHvx57NqbwP5wvRFZMHT5FK+8y7VR4BjuBwnpaXQtao+Fw08F3qvoVjvvxjRY4LzUowy4PXuYH3eUoTuuTFtx0JBmUEHacHcYoRaG67gyxOhrFAMzA1O9poczja0sMj277uaFOdJSXQNWVItqH35bm0GrE1GP8g==]]></Encrypt></xml>
            if (strXml == null || strXml.length() <= 0)
                return null;

            // 将字符串转化为XML文档对象
            Document document = DocumentHelper.parseText(strXml);
            // 获得文档的根节点
            Element root = document.getRootElement();
            // 遍历根节点下所有子节点
            Iterator<?> iter = root.elementIterator();

            // 遍历所有结点
            msg = new ReceiveXmlEntity();
            Class<?> c = Class.forName("com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity");
            msg = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象

            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                Field field = c.getDeclaredField(ele.getName());
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
                method.invoke(msg, ele.getText());
            }
            return msg;
        } catch (Exception e) {
            throw new Exception("微信消息解析错误");
        }
    }
}
