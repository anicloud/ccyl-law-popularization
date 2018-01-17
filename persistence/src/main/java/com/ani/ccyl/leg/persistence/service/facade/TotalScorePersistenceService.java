package com.ani.ccyl.leg.persistence.service.facade;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.persistence.po.TotalScorePO;

public interface TotalScorePersistenceService {
    void updateTotalScore(TotalScorePO totalScorePO);
    TotalScorePO findByAccountId(Integer accountId);
    void updateThumbUp(Integer accountId, ProvinceEnum province);
    void updateSignInCount(Integer accountId, ProvinceEnum province);
    void updateInviteCount(Integer accountId, ProvinceEnum province);
    void updateShareCount(Integer accountId, ProvinceEnum province);
}
