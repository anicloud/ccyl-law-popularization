package com.ani.ccyl.leg.commons.dto.wechat;

import java.io.Serializable;

/**
 * 消息基类 
 * Created by lihui on 17-12-4.
 */
public class BaseMessage implements Serializable{
	private static final long serialVersionUID = -3543436250306113064L;
	//接收方账号（收到的openId）
	private String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间(整型)
	private long CreateTime;
	//消息类型（text/music/news）
	private String MsgType;
	//位0x0001 被标记时，星标刚收到的消息
	private int FuncFlag;
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
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public int getFuncFlag() {
		return FuncFlag;
	}
	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}
