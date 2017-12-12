package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.utils.Encrypt;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by lihui on 17-6-27.
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	private AccountService accountService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		if(info == null) {
			return false;
		}
		UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
		AccountDto accountDto = accountService.findByAccountName(usertoken.getUsername());
		if(accountDto.getAccountName().equals(accountDto.getOpenId()))
			return true;
		Object tokenCredentials = Encrypt.md5hash(String.valueOf(usertoken.getPassword()),accountDto.getOpenId());
		Object accountCredentials = getCredentials(info);

		return equals(tokenCredentials, accountCredentials);
	}
}
