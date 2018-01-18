package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
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
    public void updateThumbUp(Integer accountId, ProvinceEnum province) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
           TotalScorePO scorePO=scorePOS.get(0);
            scorePO.setThumbUpCount(scorePO.getThumbUpCount()+1);
            scorePO.setScore(scorePO.getScore()+Constants.Score.THUMB_UP_SCORE);
            scorePO.setProvince(province);
           totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        } else {
            TotalScorePO totalScorePO = new TotalScorePO();
            totalScorePO.setScore(Constants.Score.THUMB_UP_SCORE);
            totalScorePO.setProvince(province);
            totalScorePO.setAccountId(accountId);
            totalScorePO.setThumbUpCount(1);
            totalScoreMapper.insertSelective(totalScorePO);
        }

    }

    @Override
    public void updateSignInCount(Integer accountId, ProvinceEnum province) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            scorePO.setSignInCount(scorePO.getSignInCount()+1);
            scorePO.setScore(scorePO.getScore()+ Constants.Score.SIGN_IN_SCORE);
            scorePO.setProvince(province);
            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        } else {
            TotalScorePO totalScorePO = new TotalScorePO();
            totalScorePO.setAccountId(accountId);
            totalScorePO.setSignInCount(1);
            totalScorePO.setProvince(province);
            totalScorePO.setScore(Constants.Score.SIGN_IN_SCORE);
            totalScoreMapper.insertSelective(totalScorePO);
        }

    }

    @Override
    public void updateInviteCount(Integer accountId, ProvinceEnum province) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            scorePO.setInviteCount(scorePO.getInviteCount()+1);
            scorePO.setScore(scorePO.getScore()+Constants.Score.INVITE_SC0RE);
            scorePO.setProvince(province);
            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        } else {
            TotalScorePO totalScorePO = new TotalScorePO();
            totalScorePO.setProvince(province);
            totalScorePO.setAccountId(accountId);
            totalScorePO.setScore(Constants.Score.INVITE_SC0RE);
            totalScorePO.setInviteCount(1);
            totalScoreMapper.insertSelective(totalScorePO);
        }
    }

    @Override
    public void updateShareCount(Integer accountId, ProvinceEnum province) {
        TotalScorePO scoreParam=new TotalScorePO();
        scoreParam.setAccountId(accountId);
        List<TotalScorePO> scorePOS = totalScoreMapper.select(scoreParam);
        if(scorePOS!=null && scorePOS.size()!=0){
            TotalScorePO scorePO=scorePOS.get(0);
            scorePO.setShareCount(scorePO.getShareCount()+1);
            scorePO.setScore(scorePO.getScore()+Constants.Score.SHARE_SCORE);
            scorePO.setProvince(province);
            totalScoreMapper.updateByPrimaryKeySelective(scorePO);
        } else {
            TotalScorePO totalScorePO = new TotalScorePO();
            totalScorePO.setProvince(province);
            totalScorePO.setAccountId(accountId);
            totalScorePO.setScore(Constants.Score.SHARE_SCORE);
            totalScorePO.setShareCount(1);
            totalScoreMapper.insertSelective(totalScorePO);
        }
    }
}
