package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.mapper.QuestionMapper;
import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import com.ani.ccyl.leg.service.adapter.AccountAdapter;
import com.ani.ccyl.leg.service.adapter.QuestionAdapter;
import com.ani.ccyl.leg.service.adapter.ScoreRecordAdapter;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-12-15.
 */
@Service
public class ScoreRecordServiceImpl implements ScoreRecordService{
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId) {
        if(accountId != null && score != null && srcType != null && srcId != null) {
            ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
            scoreRecordPO.setAccountId(accountId);
            switch (srcType.getCode()) {
                case 1:
                    scoreRecordPO.setSrcQuestionId(srcId);
                    List<ScoreRecordPO> scoreRecordPOs = scoreRecordMapper.select(scoreRecordPO);
                    if(scoreRecordPOs.size()==0) {
                        scoreRecordPO.setSelfAnswer(answer);
                        scoreRecordPO.setScore(score);
                        scoreRecordPO.setSrcType(srcType);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                    }
                    break;
                case 2:
                case 3:
                    scoreRecordPO.setSrcAccountId(srcId);
                    List<ScoreRecordPO> scoreRecordPOs1 = scoreRecordMapper.findByConditions(scoreRecordPO);
                    if(scoreRecordPOs1==null||scoreRecordPOs1.size()==0) {
                        scoreRecordPO.setScore(score);
                        scoreRecordPO.setSrcType(srcType);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                    }
                    break;
            }
        }
    }

    @Override
    public List<ScoreRecordDto> findDailyScoreRecoreds(Integer accountId) {
        List<ScoreRecordPO> recordPOs = scoreRecordMapper.findDailyScoreRecords(accountId);
        List<ScoreRecordDto> scoreRecordDtos = ScoreRecordAdapter.fromPOList(recordPOs);
        if(scoreRecordDtos != null) {
            for(ScoreRecordDto scoreRecordDto:scoreRecordDtos) {
                QuestionDto questionDto = QuestionAdapter.fromPO(questionMapper.selectByPrimaryKey(scoreRecordDto.getSrcQuestion().getId()));
                scoreRecordDto.setSrcQuestion(questionDto);
                AccountDto accountDto = AccountAdapter.fromPO(accountMapper.selectByPrimaryKey(scoreRecordDto.getSrcAccount().getId()));
                scoreRecordDto.setSrcAccount(accountDto);
            }
        }
        return scoreRecordDtos;
    }

    @Override
    public TotalScoreDto findTotalScore(Integer accountId) {
        return scoreRecordMapper.findTotalScore(accountId);
    }

    @Override
    public DailyTotalScoreDto findDailyTotalScore(Integer accountId, ScoreSrcTypeEnum srcType) {
        ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
        scoreRecordPO.setAccountId(accountId);
        scoreRecordPO.setSrcType(srcType);
        Integer score = scoreRecordMapper.findDailyTotalScore(scoreRecordPO);
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        DailyTotalScoreDto scoreDto = new DailyTotalScoreDto();
        scoreDto.setAccountId(accountId);
        scoreDto.setName(accountPO.getNickName());
        scoreDto.setScore(score);
        scoreDto.setPortrait(accountPO.getPortrait());
        return scoreDto;
    }

    @Override
    public List<Top20Dto> findDailyTop20() {
        List<ScoreRecordPO> scoreRecordPOs = scoreRecordMapper.findDailyTop20();
        List<Top20Dto> top20Dtos = new ArrayList<>();
        ScoreRecordPO scoreRecordParam = new ScoreRecordPO();
        if(scoreRecordPOs != null && scoreRecordPOs.size()>0) {
            for(ScoreRecordPO scoreRecordPO:scoreRecordPOs) {
                Top20Dto top20Dto = new Top20Dto();
                top20Dto.setId(scoreRecordPO.getAccountId());
                scoreRecordParam.setAccountId(scoreRecordPO.getAccountId());
                top20Dto.setScore(scoreRecordMapper.findDailyTotalScore(scoreRecordParam));
                AccountPO accountPO = accountMapper.selectByPrimaryKey(scoreRecordPO.getAccountId());
                top20Dto.setName(accountPO.getNickName());
                top20Dto.setPortrat(accountPO.getPortrait());
                top20Dtos.add(top20Dto);
            }
        }
        return top20Dtos;
    }
}
