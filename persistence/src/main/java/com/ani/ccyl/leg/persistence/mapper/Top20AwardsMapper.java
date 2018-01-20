package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.Top20AwardsPO;

import java.sql.Timestamp;
import java.util.List;

public interface Top20AwardsMapper extends SysMapper<Top20AwardsPO> {
    Top20AwardsPO findByType(Integer type);
    Top20AwardsPO findByAccountId(Integer accountId);
    List<Top20AwardsPO> findByDate(String createTime);
}
