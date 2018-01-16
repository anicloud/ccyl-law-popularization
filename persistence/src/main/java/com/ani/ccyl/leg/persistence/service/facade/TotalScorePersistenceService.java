package com.ani.ccyl.leg.persistence.service.facade;

import com.ani.ccyl.leg.persistence.po.TotalScorePO;

public interface TotalScorePersistenceService {
    void updateTotalScore(TotalScorePO totalScorePO);
    TotalScorePO findByAccountId(Integer accountId);
}
