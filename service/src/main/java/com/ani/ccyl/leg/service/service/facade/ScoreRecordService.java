package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;

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
    Boolean findIsSignIn(Integer accountId);
    TotalSignInDto findTotalSignIn(Integer accountId);

    void updateConvertAward(Integer accountId, AwardTypeEnum awardType);

    List<MyAwardDto> findMyAward(Integer accountId);

    List<AwardDto> findAllAwards(Integer accountId);
}
