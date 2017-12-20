package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.dto.TotalScoreDto;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;

import java.util.List;

/**
 * Created by lihui on 17-12-15.
 */
public interface ScoreRecordService {
    void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId);
    List<ScoreRecordDto> findDailyScoreRecoreds(Integer accountId);

    TotalScoreDto findTotalScore(Integer accountId);
    Integer findDailyTotalScore(Integer accountId, ScoreSrcTypeEnum srcType);
}
