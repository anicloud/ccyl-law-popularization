package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.utils.Encrypt;
import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.service.adapter.AccountAdapter;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-12.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public AccountDto insertAccount(JSONObject wechatObj) {
        String openId = wechatObj.getString("openid");
        String nickName = wechatObj.getString("nickname");
        String sex = wechatObj.getString("sex");
        String province = wechatObj.getString("province");
        String portrait = wechatObj.getString("headimgurl");
        AccountPO accountPO = new AccountPO();
        accountPO.setOpenId(openId);
        List<AccountPO> accountPOs = accountMapper.select(accountPO);
        if(accountPOs.size()==0) {
            accountPO.setNickName(nickName);
            accountPO.setAccountName(openId);
            accountPO.setAccountPwd(Encrypt.md5hash(Constants.DEFAULT_PWD,openId));
            accountPO.setDel(false);
            accountPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            accountPO.setPortrait(portrait);
            accountPO.setSex(sex.equals("1"));
            accountMapper.insertSelective(accountPO);
        } else {
            accountPO = accountPOs.get(0);
        }
        return AccountAdapter.fromPO(accountPO);
    }

    @Override
    public AccountDto findByAccountName(String accountName) {
        AccountPO accountPO = new AccountPO();
        accountPO.setAccountName(accountName);
        List<AccountPO> accountPOs = accountMapper.select(accountPO);
        if(accountPOs.size()>0)
            return AccountAdapter.fromPO(accountPOs.get(0));
        return null;
    }
}
