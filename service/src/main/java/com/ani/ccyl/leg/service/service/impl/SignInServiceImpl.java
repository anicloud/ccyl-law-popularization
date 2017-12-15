package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.persistence.mapper.SignInMapper;
import com.ani.ccyl.leg.persistence.po.SignInPO;
import com.ani.ccyl.leg.service.service.facade.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-15.
 */
@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private SignInMapper signInMapper;
    @Override
    public void insertSignIn(Integer accountId) {
        SignInPO signInPO = new SignInPO();
        signInPO.setCrateTime(new Timestamp(System.currentTimeMillis()));
        signInPO.setAccountId(accountId);
        SignInPO result = signInMapper.findByConditions(signInPO);
        if(result == null) {
            signInMapper.insertSelective(signInPO);
        }
    }
}
