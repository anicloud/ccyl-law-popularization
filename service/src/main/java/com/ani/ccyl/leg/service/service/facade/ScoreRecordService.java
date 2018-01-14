package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.dto.MySelfRankDto;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-12-15.
 */
public interface ScoreRecordService {
    void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId);
    List<ScoreRecordDto> findDailyScoreRecoreds(Integer accountId);

    TotalScoreDto findTotalScore(Integer accountId);
    DailyTotalScoreDto findDailyTotalScore(Integer accountId, ScoreSrcTypeEnum srcType);
    List<Top20Dto> findDailyTop20() throws UnsupportedEncodingException;

    Boolean findIsThumbUp(Integer srcId, Integer toAccountId);
    Boolean findIsSignIn(Integer accountId);
    TotalSignInDto findTotalSignIn(Integer accountId);

    void updateConvertAward(Integer accountId, AwardTypeEnum awardType);

    List<MyAwardDto> findMyAward(Integer accountId);

    /**查找所有我用积分兑换的奖品**/
    List<MyAwardDto> findMyConvertAward(Integer accountId);

    List<AwardDto> findAllAwards(Integer accountId);

    String updateTop20AwardByAccountId(Integer accountId);
    String updateLucky20AwardByAccountId(Integer accountId);

    MySelfRankDto findSelfRank(Integer accountId);


    Map<String,Object> findIsTop20Yesterday(Integer accountId);
}
