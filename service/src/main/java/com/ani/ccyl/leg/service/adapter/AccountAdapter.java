package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.persistence.po.AccountPO;

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
                    accountPO.getSex()
            );
        return null;
    }
}
