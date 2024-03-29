package com.ani.ccyl.leg.commons.dto.wechat;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-2.
 */
public class ReceiveXmlEntity implements Serializable{
    private static final long serialVersionUID = -7583692106296155296L;
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String MsgId;
    private String Event;
    private String EventKey;
    private String Ticket;
    private String Latitude;
    private String Longitude;
    private String Precision;
    private String PicUrl;
    private String MediaId;
    private String Title;
    private String Description;
    private String URL;
    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;
    private String Content;
    private String Format;
    private String Recognition;
    private String Encrypt;
    private String MenuId;

    public ReceiveXmlEntity() {
    }

    public ReceiveXmlEntity(String toUserName, String fromUserName, String createTime, String msgType, String msgId, String event, String eventKey, String ticket, String latitude, String longitude, String precision, String picUrl, String mediaId, String title, String description, String URL, String location_X, String location_Y, String scale, String label, String content, String format, String recognition, String encrypt, String menuId) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        MsgId = msgId;
        Event = event;
        EventKey = eventKey;
        Ticket = ticket;
        Latitude = latitude;
        Longitude = longitude;
        Precision = precision;
        PicUrl = picUrl;
        MediaId = mediaId;
        Title = title;
        Description = description;
        this.URL = URL;
        Location_X = location_X;
        Location_Y = location_Y;
        Scale = scale;
        Label = label;
        Content = content;
        Format = format;
        Recognition = recognition;
        Encrypt = encrypt;
        MenuId = menuId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    public String getRecognition() {
        return Recognition;
    }
    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
    public String getFormat() {
        return Format;
    }
    public void setFormat(String format) {
        Format = format;
    }
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public String getLocation_X() {
        return Location_X;
    }
    public void setLocation_X(String locationX) {
        Location_X = locationX;
    }
    public String getLocation_Y() {
        return Location_Y;
    }
    public void setLocation_Y(String locationY) {
        Location_Y = locationY;
    }
    public String getScale() {
        return Scale;
    }
    public void setScale(String scale) {
        Scale = scale;
    }
    public String getLabel() {
        return Label;
    }
    public void setLabel(String label) {
        Label = label;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String url) {
        URL = url;
    }
    public String getPicUrl() {
        return PicUrl;
    }
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
    public String getMediaId() {
        return MediaId;
    }
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    public String getEventKey() {
        return EventKey;
    }
    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
    public String getTicket() {
        return Ticket;
    }
    public void setTicket(String ticket) {
        Ticket = ticket;
    }
    public String getLatitude() {
        return Latitude;
    }
    public void setLatitude(String latitude) {
        Latitude = latitude;
    }
    public String getLongitude() {
        return Longitude;
    }
    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
    public String getPrecision() {
        return Precision;
    }
    public void setPrecision(String precision) {
        Precision = precision;
    }
    public String getEvent() {
        return Event;
    }
    public void setEvent(String event) {
        Event = event;
    }
    public String getMsgId() {
        return MsgId;
    }
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
    public String getToUserName() {
        return ToUserName;
    }
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    public String getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    public String getMsgType() {
        return MsgType;
    }
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
