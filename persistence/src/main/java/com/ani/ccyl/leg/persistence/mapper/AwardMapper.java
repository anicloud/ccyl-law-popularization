package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.AwardPO;

/**
 * Created by lihui on 17-12-22.
 */
public interface AwardMapper extends SysMapper<AwardPO>{
    Boolean findIsAward(Integer accountId);
}
