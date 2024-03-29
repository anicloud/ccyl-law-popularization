package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.commons.dto.ProvinceInfoDto;
import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DailyTotalScorePO;

import java.util.List;
import java.util.Map;

public interface DailyTotalScoreMapper extends SysMapper<DailyTotalScorePO> {
    List<DailyTotalScorePO> findTop20(String date);
    Integer findRankByAccountId(Map<String,Object> paramMap);

    List<ProvinceInfoDto> findPrivanceInfo(String logDate);

    Integer findByAccAndDate(Map<String,Object> paraMMap);
}
