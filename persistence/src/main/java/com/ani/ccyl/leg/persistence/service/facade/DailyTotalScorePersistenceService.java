package com.ani.ccyl.leg.persistence.service.facade;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.persistence.po.DailyTotalScorePO;

import java.util.List;

public interface DailyTotalScorePersistenceService {
    void updateDailyTotalScore(DailyTotalScorePO dailyTotalScore);
    List<DailyTotalScorePO> findTop20(String date);
    public int findRankByAccountId(Integer accountId);
}
