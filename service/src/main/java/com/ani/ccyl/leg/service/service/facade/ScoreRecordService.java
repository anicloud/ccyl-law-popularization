package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.DailyTotalScoreDto;
import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.dto.Top20Dto;
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
    DailyTotalScoreDto findDailyTotalScore(Integer accountId, ScoreSrcTypeEnum srcType);
    List<Top20Dto> findDailyTop20();

    Boolean findIsThumbUp(Integer accountId, Integer toAccountId);
}
