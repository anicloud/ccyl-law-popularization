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
            if(totalScorePO.getScore() != null) {
                totalScorePO.setScore(totalScorePO.getScore()+scorePO.getScore());
            }
            totalScorePO.setId(scorePO.getId());
            totalScoreMapper.updateByPrimaryKeySelective(totalScorePO);
        }else {
            totalScoreMapper.insertSelective(totalScorePO);
        }

    }

    @Override
    public TotalScorePO findByAccountId(Integer accountId) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            return  scorePOS.get(0);
        }
        return null;
    }

    @Override
    public void updateThumbUp(Integer accountId) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
           TotalScorePO scorePO=scorePOS.get(0);
           if (scorePO.getThumbUpCount()!=null){
               scorePO.setThumbUpCount(scorePO.getThumbUpCount()+1);
           }else {
               scorePO.setInviteCount(1);
           }
           totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }

    }

    @Override
    public void updateSignInCount(Integer accountId) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            if (scorePO.getSignInCount()!=null){
                scorePO.setSignInCount(scorePO.getSignInCount()+1);

            }else {
                scorePO.setSignInCount(1);
            }
            totalScoreMapper.updateByPrimaryKeySelective(scorePO);

        }

    }

    @Override
    public void updateInviteCount(Integer accountId) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            if (scorePO.getInviteCount()!=null){
                scorePO.setInviteCount(scorePO.getInviteCount()+1);
            }else {
                scorePO.setInviteCount(1);
            }

            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }
    }

    @Override
    public void updateShareCount(Integer accountId) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            if (scorePO.getShareCount()!=null){
                scorePO.setShareCount(scorePO.getShareCount()+1);
            }else {
                scorePO.setShareCount(1);
            }

            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        }
    }
}
