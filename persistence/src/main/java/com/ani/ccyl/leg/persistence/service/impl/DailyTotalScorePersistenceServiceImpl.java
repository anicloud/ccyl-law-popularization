package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.persistence.mapper.DailyTotalScoreMapper;
import com.ani.ccyl.leg.persistence.po.DailyTotalScorePO;
import com.ani.ccyl.leg.persistence.service.facade.DailyTotalScorePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DailyTotalScorePersistenceServiceImpl implements DailyTotalScorePersistenceService {
    @Autowired
    DailyTotalScoreMapper dailyTotalScoreMapper;
    @Override
    public void updateDailyTotalScore(DailyTotalScorePO dailyTotalScore) {
        DailyTotalScorePO scoreParam=new DailyTotalScorePO();
        scoreParam.setAccountId(dailyTotalScore.getAccountId());
        scoreParam.setDate(dailyTotalScore.getDate());
        List<DailyTotalScorePO> totalScorePOList=dailyTotalScoreMapper.select(scoreParam);
        if (totalScorePOList!=null && totalScorePOList.size()!=0){
            DailyTotalScorePO scorePO=totalScorePOList.get(0);
            scorePO.setScore(scorePO.getScore()+dailyTotalScore.getScore());
            dailyTotalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }else {
            dailyTotalScoreMapper.insertSelective(dailyTotalScore);
        }
    }

    @Override
    public List<DailyTotalScorePO> findTop20(String date) {
        DailyTotalScorePO scoreParam=new DailyTotalScorePO();
        scoreParam.setDate(date);
        List<DailyTotalScorePO> scorePOS= dailyTotalScoreMapper.findTop20(date);
        scoreParam.setLogDate(date);
       List<DailyTotalScorePO> scorePOS= dailyTotalScoreMapper.findTop20(date);
        return scorePOS;
    }
    @Override
    public int findRankByAccountId(Integer accountId){
        return 0;
    }

    @Override
    public DailyTotalScorePO findByAccountId(Integer accountId, String logDate) {
        return null;
    }

}
