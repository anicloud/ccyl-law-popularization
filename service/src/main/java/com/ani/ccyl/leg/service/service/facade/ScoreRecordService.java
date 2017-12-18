package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;

/**
 * Created by lihui on 17-12-15.
 */
public interface ScoreRecordService {
    void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId);
}
