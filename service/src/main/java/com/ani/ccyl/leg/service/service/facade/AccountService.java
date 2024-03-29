package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihui on 17-12-12.
 */
public interface AccountService {
    AccountDto insertAccount(JSONObject wechatObj) throws UnsupportedEncodingException;
    void saveSelfInfo(AccountDto accountDto);
    AccountDto findByAccountName(String accountName);

    AccountDto findByOpenId(String openId);

    AccountDto findById(Integer id);

    Boolean findIsInfoCompleted(Integer id);

    String getCsvFile() throws UnsupportedEncodingException;
}
