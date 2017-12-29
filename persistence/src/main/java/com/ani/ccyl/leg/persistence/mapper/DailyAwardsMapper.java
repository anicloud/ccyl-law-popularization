package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DailyAwardsPO;

/**
 * Created by lihui on 17-12-28.
 */
public interface DailyAwardsMapper extends SysMapper<DailyAwardsPO>{
    DailyAwardsPO findByType(Integer type);
    Boolean findIsAwardToday(Integer accountId);
    DailyAwardsPO findTopOrLuckyByAccountId(Integer accountId);
}
