package com.ani.ccyl.leg.commons.dto.wechat;
/**
 * 响应文本消息
 * Created by lihui on 17-12-4.
 */
public class TextMessage extends BaseMessage{
	//回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
