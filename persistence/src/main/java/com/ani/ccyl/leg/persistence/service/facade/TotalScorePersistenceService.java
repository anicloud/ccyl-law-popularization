package com.ani.ccyl.leg.persistence.service.facade;

import com.ani.ccyl.leg.persistence.po.TotalScorePO;

public interface TotalScorePersistenceService {
    void updateTotalScore(TotalScorePO totalScorePO);
    TotalScorePO findByAccountId(Integer accountId);
    void updateThumbUp(Integer accountId);
    void updateSignInCount(Integer accountId);
    void updateInviteCount(Integer accountId);
    void updateShareCount(Integer accountId);
}
