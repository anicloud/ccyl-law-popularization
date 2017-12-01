package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.commons.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by lihui on 17-6-27.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		if(info == null) {
			return false;
		}
		UsernamePasswordToken usertoken = (UsernamePasswordToken) token; 
		
		Object tokenCredentials = Encrypt.md5hash(String.valueOf(usertoken.getPassword()),usertoken.getUsername());
		Object accountCredentials = getCredentials(info);  

		return equals(tokenCredentials, accountCredentials);
	}
}
