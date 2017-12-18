package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.persistence.po.AccountPO;

import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
public class AccountAdapter {
    public static AccountDto fromPO(AccountPO accountPO) {
        if(accountPO != null)
            return new AccountDto(
                    accountPO.getId(),
                    accountPO.getOpenId(),
                    accountPO.getAccountName(),
                    accountPO.getAccountPwd(),
                    accountPO.getPhone(),
                    accountPO.getEmail(),
                    accountPO.getAddress(),
                    accountPO.getProvince(),
                    accountPO.getNickName(),
                    accountPO.getPortrait(),
                    accountPO.getReward(),
                    accountPO.getSex(),
                    accountPO.getOrgName(),
                    accountPO.getAge(),
                    accountPO.getName(),
                    null
            );
        return null;
    }

    public static AccountPO fromDto(AccountDto accountDto) {
        if(accountDto != null)
            return new AccountPO(
                    accountDto.getId()==null?null:accountDto.getId(),
                    accountDto.getOpenId(),
                    accountDto.getAccountName(),
                    accountDto.getAccountPwd(),
                    accountDto.getPhone(),
                    accountDto.getEmail(),
                    accountDto.getAddress(),
                    accountDto.getProvince(),
                    accountDto.getNickName(),
                    accountDto.getPortrait(),
                    accountDto.getIsReward(),
                    null,
                    new Timestamp(System.currentTimeMillis()),
                    false,
                    accountDto.getSex(),
                    accountDto.getOrgName(),
                    accountDto.getAge(),
                    accountDto.getName()
            );
        return null;
    }
}
