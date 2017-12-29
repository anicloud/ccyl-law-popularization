package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DailyTop20PO;

/**
 * Created by lihui on 17-12-27.
 */
public interface DailyTop20Mapper extends SysMapper<DailyTop20PO> {
    DailyTop20PO findByAccountId(Integer accountId);
}
