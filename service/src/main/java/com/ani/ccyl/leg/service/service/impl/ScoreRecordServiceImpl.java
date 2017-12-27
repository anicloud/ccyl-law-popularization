package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.*;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.po.AwardPO;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;
import com.ani.ccyl.leg.persistence.service.facade.ShareRelationPersistenceService;
import com.ani.ccyl.leg.service.adapter.AccountAdapter;
import com.ani.ccyl.leg.service.adapter.QuestionAdapter;
import com.ani.ccyl.leg.service.adapter.ScoreRecordAdapter;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
    @Autowired
    private ShareRelationPersistenceService shareRelationPersistenceService;
    @Autowired
    private ShareRelationMapper shareRelationMapper;
    @Autowired
    private AwardMapper awardMapper;

    @Override
    public void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId) {
        if(accountId != null && score != null && srcType != null && srcId != null) {
            ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
            scoreRecordPO.setAccountId(accountId);
            ShareRelationPO shareRelationPO = shareRelationPersistenceService.findBySharedId(accountId);
            if(shareRelationPO != null&&!shareRelationPO.getPartIn()) {
                ScoreRecordPO shareRecord = new ScoreRecordPO();
                shareRecord.setAccountId(shareRelationPO.getShareId());
                shareRecord.setScore(Constants.Score.INVITE_SC0RE);
                shareRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
                shareRecord.setSrcType(ScoreSrcTypeEnum.INVITE);
                shareRecord.setSrcAccountId(shareRelationPO.getSharedId());
                scoreRecordMapper.insertSelective(shareRecord);
                shareRelationPO.setPartIn(true);
                shareRelationMapper.updateByPrimaryKeySelective(shareRelationPO);
            }
            switch (srcType.getCode()) {
                case 1:
                    scoreRecordPO.setSrcQuestionId(srcId);
                    List<ScoreRecordPO> scoreRecordPOs = scoreRecordMapper.select(scoreRecordPO);
                    if(scoreRecordPOs.size()==0) {
                        scoreRecordPO.setSelfAnswer(answer);
                        scoreRecordPO.setScore(score);
                        scoreRecordPO.setSrcType(srcType);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordPO.setQuestionTime(1);
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                    } else {
                        scoreRecordPO = scoreRecordPOs.get(0);
                        if(scoreRecordPO.getQuestionTime() != null && scoreRecordPO.getQuestionTime()==1) {
                            scoreRecordPO.setSelfAnswer(answer);
                            scoreRecordPO.setScore(score);
                            scoreRecordPO.setSrcType(srcType);
                            scoreRecordPO.setQuestionTime(2);
                            scoreRecordPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                            scoreRecordMapper.updateByPrimaryKeySelective(scoreRecordPO);
                        }
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
                case 5:
                    if(scoreRecordMapper.findDailyShareCount(accountId)<5) {
                        scoreRecordPO.setSrcType(ScoreSrcTypeEnum.SHARE);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordPO.setScore(score);
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
        TotalScoreDto totalScoreDto = scoreRecordMapper.findTotalScore(accountId);
        totalScoreDto.setIsSignIn(scoreRecordMapper.findIsSignIn(accountId));
        return totalScoreDto;
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
        List<ScoreRecordPO> scoreRecordPOs = scoreRecordMapper.findDailyTop20(new Timestamp(System.currentTimeMillis()));
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
                top20Dto.setUpdateTime(scoreRecordPO.getUpdateTime());
                top20Dtos.add(top20Dto);
            }
        }
        return top20Dtos;
    }

    @Override
    public Boolean findIsThumbUp(Integer accountId, Integer toAccountId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("accountId", toAccountId);
        paramMap.put("srcAccountId",accountId);
        return scoreRecordMapper.findIsThumbUp(paramMap);
    }

    @Override
    public Boolean findIsSignIn(Integer accountId) {
        return scoreRecordMapper.findIsSignIn(accountId);
    }

    @Override
    public TotalSignInDto findTotalSignIn(Integer accountId) {
        List<Integer> days = new ArrayList<>();
        List<Timestamp> totalSignIn = scoreRecordMapper.findTotalSignIn(accountId);
        if(totalSignIn != null) {
            Calendar calendar = Calendar.getInstance();
            for(Timestamp timestamp:totalSignIn) {
                calendar.setTime(timestamp);
                days.add(calendar.get(Calendar.DAY_OF_MONTH));
            }
        }
        return new TotalSignInDto(days,scoreRecordMapper.findIsSignIn(accountId));
    }

    @Override
    public void updateConvertAward(Integer accountId, AwardTypeEnum awardType) {
        TotalScoreDto totalScoreDto = findTotalScore(accountId);
        if(totalScoreDto.getScore()>=awardType.findScore() && !awardMapper.findIsAward(accountId)) {
            AwardPO awardPO = new AwardPO(null,accountId,awardType.findScore(),awardType,null,new Timestamp(System.currentTimeMillis()),false);
            awardMapper.insertSelective(awardPO);
        } else {
            throw new RuntimeException("积分不足或已经领取过了～");
        }
    }

    @Override
    public AwardDto findAwardScore(Integer accountId) {
        AwardPO awardParam = new AwardPO();
        awardParam.setAccountId(accountId);
        awardParam.setDel(false);
        List<AwardPO> awardPOs = awardMapper.select(awardParam);
        AwardDto awardDto = new AwardDto();
        Integer totalScore = findTotalScore(accountId).getScore();
        awardDto.setLastScore(totalScore);
        if(awardPOs.size()>0) {
            AwardPO awardPO = awardPOs.get(0);
            awardDto.setAwardType(awardPO.getAwardType());
            awardDto.setLastScore(totalScore-awardPO.getAwardType().findScore());
        }
        return awardDto;
    }

}
