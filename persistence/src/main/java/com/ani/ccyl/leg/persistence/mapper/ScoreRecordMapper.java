package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.dto.TotalScoreDto;
import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.QuestionPO;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-12-15.
 */
public interface ScoreRecordMapper extends SysMapper<ScoreRecordPO> {
    QuestionPO findCurrentQuestion(Integer accountId);
    List<ScoreRecordPO> findDailyScoreRecords(Integer accountId);
    ScoreRecordDto findById(Integer id);
    TotalScoreDto findTotalScore(Integer accountId);

    List<ScoreRecordPO> findByConditions(ScoreRecordPO scoreRecordPO);
    List<ScoreRecordPO> findDailyTop20();
    Integer findDailyTotalScore(ScoreRecordPO scoreRecordPO);
    Boolean findIsThumbUp(Map<String, Object> paramMap);
    Boolean findIsSignIn(Integer accountId);
    List<Timestamp> findTotalSignIn(Integer accountId);
}
