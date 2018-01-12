package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.CsvDto;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.commons.utils.Encrypt;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.service.facade.AccountPersistenceService;
import com.ani.ccyl.leg.service.adapter.AccountAdapter;
import com.ani.ccyl.leg.service.service.facade.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-12.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountPersistenceService accountPersistenceService;
    @Override
    public AccountDto insertAccount(JSONObject wechatObj) throws UnsupportedEncodingException {
        String openId = wechatObj.getString("openid");
        String nickName = wechatObj.getString("nickname");
        String sex = wechatObj.getString("sex");
        String province = wechatObj.getString("province");
        String portrait = wechatObj.getString("headimgurl");
        AccountPO accountPO = new AccountPO();
        accountPO.setOpenId(openId);
        List<AccountPO> accountPOs = accountMapper.select(accountPO);
        accountPO.setSex(sex.equals("1"));
        accountPO.setNickName(URLEncoder.encode(nickName,"utf-8"));
        accountPO.setPortrait(portrait);
        boolean isNew = true;
        if(accountPOs.size()==0) {
            accountPO.setAccountName(openId);
            accountPO.setAccountPwd(Encrypt.md5hash(Constants.DEFAULT_PWD,openId));
            accountPO.setDel(false);
            accountPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            accountMapper.insertSelective(accountPO);
        } else {
            accountPO.setId(accountPOs.get(0).getId());
            accountPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            accountMapper.updateByPrimaryKeySelective(accountPO);
            isNew = false;
        }
        AccountDto accountDto = AccountAdapter.fromPO(accountPO);
        accountDto.setNew(isNew);
        return accountDto;
    }

    @Override
    public void saveSelfInfo(AccountDto accountDto) {
        if(accountDto != null && accountDto.getId() != null) {
            accountDto.setOpenId(null);
            accountDto.setAccountName(null);
            accountDto.setAccountPwd(null);
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

    @Override
    public AccountDto findById(Integer id) {
        return AccountAdapter.fromPO(accountMapper.selectByPrimaryKey(id));
    }

    @Override
    public Boolean findIsInfoCompleted(Integer id) {
        return accountPersistenceService.findIsInfoComplete(id);
    }

    @Override
    public void getCsvFile(Integer accountId) {
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        CsvDto csvDto = new CsvDto(
                accountPO.getNickName(),accountPO.getProvince().getValue(),accountPO.getName(),accountPO.getPhone(),accountPO.getEmail(),accountPO.getSex()?"男":"女",accountPO.getOrgName(),accountPO.getAge()+"",null
        );
    }
}
