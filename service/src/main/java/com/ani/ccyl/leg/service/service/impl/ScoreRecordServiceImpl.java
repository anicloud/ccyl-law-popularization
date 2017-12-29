package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.*;
import com.ani.ccyl.leg.persistence.po.*;
import com.ani.ccyl.leg.persistence.service.facade.AccountPersistenceService;
import com.ani.ccyl.leg.persistence.service.facade.ShareRelationPersistenceService;
import com.ani.ccyl.leg.service.adapter.AccountAdapter;
import com.ani.ccyl.leg.service.adapter.QuestionAdapter;
import com.ani.ccyl.leg.service.adapter.ScoreRecordAdapter;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    private DailyAwardsMapper dailyAwardsMapper;
    @Autowired
    private DailyTop20Mapper dailyTop20Mapper;
    @Autowired
    private DailyLucky20Mapper dailyLucky20Mapper;
    @Autowired
    private AccountPersistenceService accountPersistenceService;
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
    public List<Top20Dto> findDailyTop20() throws UnsupportedEncodingException {
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
                top20Dto.setName(URLDecoder.decode(accountPO.getNickName(),"utf-8"));
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
        if(totalScoreDto.getScore()<awardType.findScore())
            throw new RuntimeException("积分不足");
        if(dailyAwardsMapper.findIsAwardToday(accountId))
            throw new RuntimeException("今天已经领取过了");
        DailyAwardsPO dailyAwardsPO = dailyAwardsMapper.findByType(awardType.getCode());
        if(dailyAwardsPO == null)
            throw new RuntimeException("今天奖品已经领取完了～");
        dailyAwardsPO.setDel(true);
        dailyAwardsPO.setAccountId(accountId);
        dailyAwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        dailyAwardsMapper.updateByPrimaryKeySelective(dailyAwardsPO);
    }

    @Override
    public List<MyAwardDto> findMyAward(Integer accountId) {
        DailyAwardsPO dailyAwardsParam = new DailyAwardsPO();
        dailyAwardsParam.setAccountId(accountId);
        dailyAwardsParam.setDel(false);
        List<DailyAwardsPO> dailyAwardsPOs = dailyAwardsMapper.select(dailyAwardsParam);
        List<MyAwardDto> myAwardDtos = new ArrayList<>();
        Integer lastScore = findTotalScore(accountId).getScore();
        if(dailyAwardsPOs!=null) {
            for(DailyAwardsPO dailyAwardsPO:dailyAwardsPOs) {
                MyAwardDto myAwardDto = new MyAwardDto();
                myAwardDto.setAwardType(dailyAwardsPO.getType());
                myAwardDto.setCreateTime(dailyAwardsPO.getUpdateTime());
                if((System.currentTimeMillis()-dailyAwardsPO.getUpdateTime().getTime()) >= 6*24*60*60*1000) {
                    myAwardDto.setIsExpired(true);
                } else {
                    myAwardDto.setCodeSecret(dailyAwardsPO.getCodeSecret());
                    myAwardDto.setIsExpired(false);
                }
                myAwardDto.setIsReceivedAward(true);
                lastScore = lastScore - dailyAwardsPO.getType().findScore();
                myAwardDtos.add(myAwardDto);
            }
        }
        for(MyAwardDto myAwardDto : myAwardDtos) {
            myAwardDto.setLastScore(lastScore);
        }
        DailyTop20PO dailyTop20PO = dailyTop20Mapper.findByAccountId(accountId);
        if(dailyTop20PO!=null) {
            if(!dailyTop20PO.getReceiveAward()) {
                MyAwardDto myAwardDto = new MyAwardDto(lastScore, AwardTypeEnum.getTopEnum(dailyTop20PO.getOrderNum()), null, false, dailyTop20PO.getReceiveAward(), dailyTop20PO.getCreateTime());
                myAwardDtos.add(myAwardDto);
            }
        } else {
            DailyLucky20PO dailyLucky20PO = dailyLucky20Mapper.findByAccountId(accountId);
            if (dailyLucky20PO!=null) {
                if (!dailyLucky20PO.getReceiveAward()) {
                    MyAwardDto myAwardDto = new MyAwardDto(lastScore, AwardTypeEnum.LUCKY, null, false, dailyLucky20PO.getReceiveAward(), dailyLucky20PO.getCreateTime());
                    myAwardDtos.add(myAwardDto);
                }
            }
        }
        return myAwardDtos;
    }

    @Override
    public List<AwardDto> findAllAwards(Integer accountId) {
        DailyAwardsPO dailyAwardsParam = new DailyAwardsPO();
        dailyAwardsParam.setAccountId(accountId);
        List<DailyAwardsPO> dailyAwardsPOs = dailyAwardsMapper.select(dailyAwardsParam);
        Integer lastScore = findTotalScore(accountId).getScore();
        if(dailyAwardsPOs!=null) {
            for(DailyAwardsPO dailyAwardsPO:dailyAwardsPOs) {
                lastScore = lastScore - dailyAwardsPO.getType().findScore();
            }
        }
        List<AwardDto> awardDtos = new ArrayList<>();
        AwardDto tencentAward = new AwardDto(AwardTypeEnum.TENCENT_VIP, dailyAwardsMapper.findByType(AwardTypeEnum.TENCENT_VIP.getCode())==null,AwardTypeEnum.TENCENT_VIP.findScore(),lastScore);
        AwardDto ofoAward = new AwardDto(AwardTypeEnum.OFO_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.OFO_COUPON.getCode())==null,AwardTypeEnum.OFO_COUPON.findScore(),lastScore);
        AwardDto fiveAward = new AwardDto(AwardTypeEnum.FIVE_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.FIVE_COUPON.getCode())==null,AwardTypeEnum.FIVE_COUPON.findScore(),lastScore);
        AwardDto tenAward = new AwardDto(AwardTypeEnum.TEN_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.TEN_COUPON.getCode())==null,AwardTypeEnum.TEN_COUPON.findScore(),lastScore);
        awardDtos.add(tencentAward);
        awardDtos.add(ofoAward);
        awardDtos.add(fiveAward);
        awardDtos.add(tenAward);

        return awardDtos;
    }

    @Override
    public String updateFindTop20OrLuckyAward(Integer accountId) {
        DailyTop20PO dailyTop20PO = dailyTop20Mapper.findByAccountId(accountId);
        String secret = "";
        if(dailyTop20PO != null && !dailyTop20PO.getReceiveAward()&&dailyAwardsMapper.findTopOrLuckyByAccountId(accountId) == null) {
            if(accountPersistenceService.findIsInfoComplete(accountId))
                throw new RuntimeException("个人信息不完整");
            DailyAwardsPO awardsPO = dailyAwardsMapper.findByType(AwardTypeEnum.getTopEnum(dailyTop20PO.getOrderNum()).getCode());
            if(awardsPO != null) {
                awardsPO.setDel(true);
                awardsPO.setAccountId(accountId);
                awardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                dailyAwardsMapper.updateByPrimaryKeySelective(awardsPO);
                dailyTop20PO.setReceiveAward(true);
                dailyTop20Mapper.updateByPrimaryKeySelective(dailyTop20PO);
            }
        } else {
            DailyLucky20PO dailyLucky20PO = dailyLucky20Mapper.findByAccountId(accountId);
            if(dailyLucky20PO!=null&&!dailyLucky20PO.getReceiveAward() && dailyAwardsMapper.findTopOrLuckyByAccountId(accountId) == null) {
                DailyAwardsPO dailyAwardsPO = dailyAwardsMapper.findByType(AwardTypeEnum.LUCKY.getCode());
                if(dailyAwardsPO != null) {
                    dailyAwardsPO.setDel(true);
                    dailyAwardsPO.setAccountId(accountId);
                    dailyAwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    dailyAwardsMapper.updateByPrimaryKeySelective(dailyAwardsPO);
                    dailyLucky20PO.setReceiveAward(true);
                    dailyLucky20Mapper.updateByPrimaryKeySelective(dailyLucky20PO);
                }
            }
        }
        return secret;
    }

}
