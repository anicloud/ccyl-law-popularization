package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.service.facade.AccountPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by lihui on 17-12-28.
 */
@Service
public class AccountPersistenceServiceImpl implements AccountPersistenceService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Boolean findIsInfoComplete(Integer accountId) {
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        if(accountPO != null) {
            return !(StringUtils.isEmpty(accountPO.getPhone()));
        }
        return false;
    }
}
