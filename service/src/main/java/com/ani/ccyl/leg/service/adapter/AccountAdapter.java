package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.persistence.po.AccountPO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
public class AccountAdapter {
    public static AccountDto fromPO(AccountPO accountPO) {
        if(accountPO != null)
            try {
                return new AccountDto(
                        accountPO.getId(),
                        accountPO.getOpenId(),
                        accountPO.getAccountName(),
                        accountPO.getAccountPwd(),
                        accountPO.getPhone(),
                        accountPO.getEmail(),
                        accountPO.getAddress(),
                        accountPO.getProvince(),
                        URLDecoder.decode(accountPO.getNickName(),"utf-8"),
                        accountPO.getPortrait(),
                        accountPO.getSex(),
                        accountPO.getOrgName(),
                        accountPO.getAge(),
                        accountPO.getName()
                );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException("nickname编码失败");
            }
        return null;
    }

    public static AccountPO fromDto(AccountDto accountDto) {
        if(accountDto != null)
            try {
                return new AccountPO(
                        accountDto.getId(),
                        accountDto.getOpenId(),
                        accountDto.getAccountName(),
                        accountDto.getAccountPwd(),
                        accountDto.getPhone(),
                        accountDto.getEmail(),
                        accountDto.getAddress(),
                        accountDto.getProvince(),
                        URLEncoder.encode(accountDto.getNickName(),"utf-8"),
                        accountDto.getPortrait(),
                        null,
                        new Timestamp(System.currentTimeMillis()),
                        false,
                        accountDto.getSex(),
                        accountDto.getOrgName(),
                        accountDto.getAge(),
                        accountDto.getName()
                );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException("nickname解码失败");
            }
        return null;
    }
}
