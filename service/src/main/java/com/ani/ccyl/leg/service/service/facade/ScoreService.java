package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;

/**
 * Created by lihui on 17-12-15.
 */
public interface ScoreService {
    void insertScore(Integer accountId, Integer score, ScoreSrcTypeEnum srcType, Integer srcId);
}
