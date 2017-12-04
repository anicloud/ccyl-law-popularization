package com.ani.ccyl.leg.commons.dto.wechat;
/**
 * 响应的音乐消息
 * Created by lihui on 17-12-4.
 */
public class MusicMessage extends BaseMessage{
	//音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
}
