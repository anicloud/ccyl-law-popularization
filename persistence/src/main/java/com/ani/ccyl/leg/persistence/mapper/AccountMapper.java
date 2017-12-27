package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;

import java.util.List;

/**
 * Created by lihui on 17-12-12.
 */
public interface AccountMapper extends SysMapper<AccountPO>{
    List<AccountPO> findNotInTop20();
}
