package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.SignInPO;

/**
 * Created by lihui on 17-12-15.
 */
public interface SignInMapper extends SysMapper<SignInPO> {
    SignInPO findByConditions(SignInPO signInPO);
}
