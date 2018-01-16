package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DailyTotalScorePO;

import java.util.List;

public interface DailyTotalScoreMapper extends SysMapper<DailyTotalScorePO> {
    List<DailyTotalScorePO> findTop20(String date);
}
