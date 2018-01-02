package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.Lucky20AwardsPO;

public interface Lucky20AwardsMapper extends SysMapper<Lucky20AwardsPO> {
    Lucky20AwardsPO findNewOne();
    Lucky20AwardsPO findByAccountId(Integer accountId);
}
