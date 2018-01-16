package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.persistence.mapper.TotalScoreMapper;
import com.ani.ccyl.leg.persistence.po.TotalScorePO;
import com.ani.ccyl.leg.persistence.service.facade.TotalScorePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalScorePersistenceServiceImpl implements TotalScorePersistenceService {
    @Autowired
    TotalScoreMapper totalScoreMapper;
    @Override
    public void updateTotalScore(TotalScorePO totalScorePO) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(totalScorePO.getAccountId());
        List<TotalScorePO> scorePOList=totalScoreMapper.select(scoreParam);
        if (scorePOList!=null && scorePOList.size()!=0){
            TotalScorePO scorePO=scorePOList.get(0);
            scorePO.setScore(scorePO.getScore()+totalScorePO.getScore());
            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }else {
            totalScoreMapper.insertSelective(totalScorePO);
        }

    }

    @Override
    public TotalScorePO findByAccountId(Integer accountId) {
        return null;
    }

    @Override
    public void updateThumbUp(Integer accountId) {

    }

    @Override
    public void updateSignInCount(Integer accountId) {

    }

    @Override
    public void updateInviteCount(Integer accountId) {

    }

    @Override
    public void updateShareCount(Integer accountId) {

    }
}
