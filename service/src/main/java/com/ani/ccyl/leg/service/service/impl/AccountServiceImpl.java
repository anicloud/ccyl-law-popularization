package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.commons.utils.Encrypt;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
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
    public void saveSelfInfo(AccountDto accountDto) {
        if(accountDto != null && accountDto.getId() != null) {
            accountDto.setOpenId(null);
            accountDto.setAccountName(null);
            accountDto.setAccountPwd(null);
            accountDto.setIsReward(null);
            accountMapper.updateByPrimaryKeySelective(AccountAdapter.fromDto(accountDto));
        } else {
            throw new RuntimeException("用户id为null");
        }
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

    @Override
    public AccountDto findByOpenId(String openId) {
        AccountPO accountPO = new AccountPO();
        accountPO.setOpenId(openId);
        List<AccountPO> accountPOs = accountMapper.select(accountPO);
        if(accountPOs.size()>0)
            return AccountAdapter.fromPO(accountPOs.get(0));
        return null;
    }
}
