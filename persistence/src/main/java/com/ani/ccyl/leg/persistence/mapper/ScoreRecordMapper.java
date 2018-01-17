package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.commons.dto.InvitedDto;
import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.commons.dto.TotalScoreDto;
import com.ani.ccyl.leg.commons.dto.MySelfRankDto;
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
    QuestionPO findCurrentQuestion(Map<String,Object> paramMap);
    ScoreRecordDto findById(Integer id);

    List<ScoreRecordPO> findByConditions(ScoreRecordPO scoreRecordPO);
    List<ScoreRecordPO> findDailyTop20(Timestamp createTime);
    Boolean findIsThumbUp(Map<String, Object> paramMap);
    Boolean findIsSignIn(Integer accountId);
    Integer findDailyCorrectCount(Integer accountId);
    Integer findDailyShareCount(Integer accountId);
    List<ScoreRecordPO> findProvinceOrder(Map<String,Object> paramMap);
    void cleanUpScore(Integer accountId);
    Integer findThumbUpCount(Integer accountId);
    List<InvitedDto> selectByAccountId(Map<String,Object> paramMap);
}
