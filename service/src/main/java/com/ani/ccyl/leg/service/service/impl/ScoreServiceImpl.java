package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.ScoreMapper;
import com.ani.ccyl.leg.persistence.po.ScorePO;
import com.ani.ccyl.leg.service.service.facade.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-15.
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public void insertScore(Integer accountId, Integer score, ScoreSrcTypeEnum srcType, Integer srcId) {
        if(accountId != null && score != null && srcType != null && srcId != null) {
            ScorePO scorePO = new ScorePO();
            scorePO.setAccountId(accountId);
            scorePO.setSrcId(srcId);
            List<ScorePO> scorePOs = scoreMapper.select(scorePO);
            if(scorePOs.size()==0) {
                scorePO.setScore(score);
                scorePO.setSrcType(srcType);
                scorePO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                scoreMapper.insertSelective(scorePO);
            }
        }
    }
}
