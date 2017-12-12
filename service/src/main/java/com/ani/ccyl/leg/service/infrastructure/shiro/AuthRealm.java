package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by lihui on 17-12-1.
 */
public class AuthRealm extends AuthorizingRealm {
    private AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        AccountDto accountDto = accountService.findByAccountName(upToken.getUsername());
        if(accountDto!=null) {
            return new SimpleAuthenticationInfo(accountDto,accountDto.getAccountPwd(),this.getName());
        } else {
            throw new AuthenticationException("用户不存在");
        }
    }
}
