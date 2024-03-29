package com.ani.ccyl.leg.commons.constants;

import java.util.Properties;

/**
 * Created by lihui on 17-12-1.
 */
public class Constants {
    public static final String LOGIN_SESSION = "LOGIN_SESSION";
    public static final String LOGIN_COOKIE = "LOGIN_COOKIE";
    public static final String SMS_CODE_TIME_SESSION = "SMS_CODE_TIME_SESSION";
    public static Properties PROPERTIES;
    public static final String DEFAULT_PWD = "123456";
    public static class WechatMsgType {
        /**
         * 返回信息类型：文本
         */
        public static final String  RESP_MESSSAGE_TYPE_TEXT = "text";
        /**
         * 返回信息类型：音乐
         */
        public static final String  RESP_MESSSAGE_TYPE_MUSIC = "music";
        /**
         * 返回信息类型：图文
         */
        public static final String  RESP_MESSSAGE_TYPE_NEWS = "news";
        /**
         * 请求信息类型：文本
         */
        public static final String  REQ_MESSSAGE_TYPE_TEXT = "text";
        /**
         * 请求信息类型：图片
         */
        public static final String  REQ_MESSSAGE_TYPE_IMAGE = "image";
        /**
         * 请求信息类型：链接
         */
        public static final String  REQ_MESSSAGE_TYPE_LINK = "link";
        /**
         * 请求信息类型：地理位置
         */
        public static final String  REQ_MESSSAGE_TYPE_LOCATION = "location";
        /**
         * 请求信息类型：音频
         */
        public static final String  REQ_MESSSAGE_TYPE_VOICE = "voice";
        /**
         * 请求信息类型：推送
         */
        public static final String  REQ_MESSSAGE_TYPE_EVENT = "event";
        /**
         * 事件类型：subscribe（订阅）
         */
        public static final String  EVENT_TYPE_SUBSCRIBE = "subscribe";
        /**
         * 事件类型：unsubscribe（取消订阅）
         */
        public static final String  EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
        /**
         * 事件类型：click（自定义菜单点击事件）
         */
        public static final String  EVENT_TYPE_CLICK= "CLICK";

        /**
         * 事件类型：view（自定义菜单点击事件,返回url）
         */
        public static final String  EVENT_TYPE_VIEW= "VIEW";
    }
    public static class Score {
        public static final Integer QUESTION_SCORE=2;
        public static final Integer THUMB_UP_SCORE=1;
        public static final Integer SIGN_IN_SCORE=5;
        public static final Integer INVITE_SC0RE =5;
        public static final Integer SHARE_SCORE = 5;
    }
}
