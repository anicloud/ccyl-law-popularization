package com.ani.ccyl.leg.commons.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Encrypt {
	/*
	 * 两个参数：1）需要加密密码，2）盐(混淆、动态值) 用户名 3）hash次数
	 * 
	 * 例如：
	 * password=123456+“123”、“abc”
	 */
	
	public static String md5(String password){
		return DigestUtils.md5Hex(password);
	}
	
	public static String md5hash(String password, String salt){
		return new Md5Hash(password, salt, 2).toString();
	}
}
