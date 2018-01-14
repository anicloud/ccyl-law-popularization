package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DailyAwardsPO;

import java.util.Map;

/**
 * Created by lihui on 17-12-28.
 */
public interface DailyAwardsMapper extends SysMapper<DailyAwardsPO>{
    DailyAwardsPO findByType(Integer type);
    Boolean findIsAwardToday(Map<String,Integer> map);
    Integer findCount(Integer type);
    Integer find78Count();
}
