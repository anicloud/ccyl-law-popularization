package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.persistence.mapper.DailyTotalScoreMapper;
import com.ani.ccyl.leg.persistence.po.DailyTotalScorePO;
import com.ani.ccyl.leg.persistence.service.facade.DailyTotalScorePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DailyTotalScorePersistenceServiceImpl implements DailyTotalScorePersistenceService {
    @Autowired
    DailyTotalScoreMapper dailyTotalScoreMapper;
    @Override
    public void updateDailyTotalScore(DailyTotalScorePO dailyTotalScore) {
        DailyTotalScorePO scoreParam=new DailyTotalScorePO();
        scoreParam.setAccountId(dailyTotalScore.getAccountId());
        scoreParam.setLogDate(dailyTotalScore.getLogDate());
        List<DailyTotalScorePO> totalScorePOList=dailyTotalScoreMapper.select(scoreParam);
        if (totalScorePOList!=null && totalScorePOList.size()!=0){
            DailyTotalScorePO scorePO=totalScorePOList.get(0);
            scorePO.setScore(scorePO.getScore()+dailyTotalScore.getScore());
            if(dailyTotalScore.getCorrectCount() != null)
                scorePO.setCorrectCount(scorePO.getCorrectCount()+1);
            scorePO.setProvince(dailyTotalScore.getProvince());
            scorePO.setQuestionTime(dailyTotalScore.getQuestionTime());
            dailyTotalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }else {
            if(dailyTotalScore.getCorrectCount() != null)
                dailyTotalScore.setCorrectCount(1);
            dailyTotalScoreMapper.insertSelective(dailyTotalScore);
        }
    }

    @Override
    public List<DailyTotalScorePO> findTop20(String date) {

        List<DailyTotalScorePO> scorePOS= dailyTotalScoreMapper.findTop20(date);
        return scorePOS;
    }

    @Override
    public int findRankByAccountId(Integer accountId, String logDate) {
        Map<String,Object> paramMap =new HashMap<>();
        paramMap.put("accountId",accountId);
        paramMap.put("logDate",logDate);
        Integer rank = dailyTotalScoreMapper.findRankByAccountId(paramMap);
        return rank==null?-1:rank;
    }


    @Override
    public DailyTotalScorePO findByAccountId(Integer accountId, String logDate) {
        DailyTotalScorePO scoreParam=new DailyTotalScorePO();
        scoreParam.setAccountId(accountId);
        scoreParam.setLogDate(logDate);
        List<DailyTotalScorePO> scorePOS = dailyTotalScoreMapper.select(scoreParam);
        if (scorePOS!=null && scorePOS.size()!=0){
            return scorePOS.get(0);
        }
        return null;

    }

    @Override
    public void updateCurrentQuestion(Integer accountId, Integer questionId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DailyTotalScorePO scoreParam=new DailyTotalScorePO();
        scoreParam.setAccountId(accountId);
        scoreParam.setLogDate(simpleDateFormat.format(new Date()));
        List<DailyTotalScorePO> scorePOS = dailyTotalScoreMapper.select(scoreParam);
        if(scorePOS != null && scorePOS.size()>0) {
            DailyTotalScorePO dailyTotalScorePO = scorePOS.get(0);
            dailyTotalScorePO.setQuestionId(questionId);
            dailyTotalScoreMapper.updateByPrimaryKeySelective(dailyTotalScorePO);
        } else {
            DailyTotalScorePO dailyTotalScorePO = new DailyTotalScorePO();
            dailyTotalScorePO.setAccountId(accountId);
            dailyTotalScorePO.setQuestionId(questionId);
            dailyTotalScorePO.setLogDate(simpleDateFormat.format(new Date()));
            dailyTotalScoreMapper.insertSelective(dailyTotalScorePO);
        }
    }

}
