package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.*;
import com.ani.ccyl.leg.commons.dto.MySelfRankDto;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.*;
import com.ani.ccyl.leg.persistence.po.*;
import com.ani.ccyl.leg.persistence.service.facade.AccountPersistenceService;
import com.ani.ccyl.leg.persistence.service.facade.DailyTotalScorePersistenceService;
import com.ani.ccyl.leg.persistence.service.facade.ShareRelationPersistenceService;
import com.ani.ccyl.leg.persistence.service.facade.TotalScorePersistenceService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lihui on 17-12-15.
 */
@Service
public class ScoreRecordServiceImpl implements ScoreRecordService{
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ShareRelationPersistenceService shareRelationPersistenceService;
    @Autowired
    private ShareRelationMapper shareRelationMapper;
    @Autowired
    private DailyAwardsMapper dailyAwardsMapper;
    @Autowired
    private AccountPersistenceService accountPersistenceService;
    @Autowired
    private Top20AwardsMapper top20AwardsMapper;
    @Autowired
    private Lucky20AwardsMapper lucky20AwardsMapper;
    @Autowired
    private DailyTotalScoreMapper dailyTotalScoreMapper;
    @Autowired
    private DailyTotalScorePersistenceService dailyTotalScorePersistenceService;
    @Autowired
    private TotalScorePersistenceService totalScorePersistenceService;
    @Override
    public void insertScore(Integer accountId, Integer score, String answer, ScoreSrcTypeEnum srcType, Integer srcId) {
        if(accountId != null && score != null && srcType != null && srcId != null) {
            ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
            scoreRecordPO.setAccountId(accountId);
            ShareRelationPO shareRelationPO = shareRelationPersistenceService.findBySharedId(accountId);
            AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(shareRelationPO != null&&!shareRelationPO.getIsPartIn()) {
                ScoreRecordPO shareRecord = new ScoreRecordPO();
                shareRecord.setAccountId(shareRelationPO.getShareId());
                shareRecord.setScore(Constants.Score.INVITE_SC0RE);
                shareRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
                shareRecord.setSrcType(ScoreSrcTypeEnum.INVITE);
                shareRecord.setSrcAccountId(shareRelationPO.getSharedId());
                scoreRecordMapper.insertSelective(shareRecord);

                updateTotalScore(Constants.Score.INVITE_SC0RE,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),null,null);
                totalScorePersistenceService.updateInviteCount(accountId);

                shareRelationPO.setIsPartIn(true);
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
                        updateTotalScore(score,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),1,score>0?1:null);
                    } else {
                        scoreRecordPO = scoreRecordPOs.get(0);
                        if(scoreRecordPO.getQuestionTime() != null && scoreRecordPO.getQuestionTime()==1) {
                            scoreRecordPO.setSelfAnswer(answer);
                            scoreRecordPO.setScore(score);
                            scoreRecordPO.setSrcType(srcType);
                            scoreRecordPO.setQuestionTime(2);
                            scoreRecordPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                            scoreRecordMapper.updateByPrimaryKeySelective(scoreRecordPO);
                            updateTotalScore(score,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),2,score>0?1:null);
                        }
                    }
                    break;
                case 2:
                    scoreRecordPO.setSrcAccountId(srcId);
                    List<ScoreRecordPO> scoreRecordPOs1 = scoreRecordMapper.findByConditions(scoreRecordPO);
                    if(scoreRecordPOs1==null||scoreRecordPOs1.size()==0) {
                        totalScorePersistenceService.updateThumbUp(accountId);
                        scoreRecordPO.setScore(score);
                        scoreRecordPO.setSrcType(srcType);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                        updateTotalScore(score,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),null,null);
                    }
                    break;
                case 3:
                    scoreRecordPO.setSrcAccountId(srcId);
                    List<ScoreRecordPO> scoreRecordPOs2 = scoreRecordMapper.findByConditions(scoreRecordPO);
                    if(scoreRecordPOs2==null||scoreRecordPOs2.size()==0) {
                        totalScorePersistenceService.updateSignInCount(accountId);
                        scoreRecordPO.setScore(score);
                        scoreRecordPO.setSrcType(srcType);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                        updateTotalScore(score,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),null,null);
                    }
                    break;
                case 4:
                    totalScorePersistenceService.updateInviteCount(accountId);
                    break;
                case 5:
                    if(scoreRecordMapper.findDailyShareCount(accountId)==0) {
                        scoreRecordPO.setSrcType(ScoreSrcTypeEnum.SHARE);
                        scoreRecordPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        scoreRecordPO.setScore(score);
                        scoreRecordMapper.insertSelective(scoreRecordPO);
                        updateTotalScore(score,accountId,accountPO.getProvince(),simpleDateFormat.format(new Date()),null,null);
                        totalScorePersistenceService.updateShareCount(accountId);
                    }
                    break;
            }
        }
    }
    private void updateTotalScore(Integer score, Integer accountId, ProvinceEnum province, String date, Integer questionTime,Integer correctCount) {
        // TODO: 2018/1/16 插入总分表
        TotalScorePO totalScorePO = new TotalScorePO(null, accountId, score, province);
        totalScorePersistenceService.updateTotalScore(totalScorePO);
        // TODO: 2018/1/16 插入每日积分表
        DailyTotalScorePO dailyTotalScorePO = new DailyTotalScorePO(null, accountId, score, date, province, questionTime, correctCount);
        dailyTotalScorePersistenceService.updateDailyTotalScore(dailyTotalScorePO);
    }

    @Override
    public TotalScoreDto findTotalScore(Integer accountId) {
        TotalScoreDto totalScoreDto = new TotalScoreDto();
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        TotalScorePO totalScorePO = totalScorePersistenceService.findByAccountId(accountId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DailyTotalScorePO dailyTotalScorePO = dailyTotalScorePersistenceService.findByAccountId(accountId,simpleDateFormat.format(new Date()));
        totalScoreDto.setNickName(accountPO.getNickName());
        totalScoreDto.setPortrait(accountPO.getPortrait());
        if(totalScorePO == null) {
            totalScoreDto.setInviteCount(0);
            totalScoreDto.setShareCount(0);
            totalScoreDto.setThumbUpCount(0);
            totalScoreDto.setSignInCount(0);
            totalScoreDto.setScore(0);
        } else {
            totalScoreDto.setInviteCount(totalScorePO.getInviteCount());
            totalScoreDto.setShareCount(totalScorePO.getShareCount());
            totalScoreDto.setThumbUpCount(totalScorePO.getThumbUpCount());
            totalScoreDto.setSignInCount(totalScorePO.getSignInCount());
            totalScoreDto.setScore(totalScorePO.getScore());
        }
        if(dailyTotalScorePO == null) {
            totalScoreDto.setQuestionTime(0);
            totalScoreDto.setQuestionCount(0);
        } else {
            totalScoreDto.setQuestionCount(dailyTotalScorePO.getCorrectCount());
            totalScoreDto.setQuestionTime(dailyTotalScorePO.getQuestionTime());
        }
        totalScoreDto.setIsSignIn(scoreRecordMapper.findIsSignIn(accountId));
        return totalScoreDto;
    }

//    @Override
//    public DailyTotalScoreDto findDailyTotalScore(Integer accountId, ScoreSrcTypeEnum srcType) {
//        ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
//        scoreRecordPO.setAccountId(accountId);
//        scoreRecordPO.setSrcType(srcType);
//        Integer score = scoreRecordMapper.findDailyTotalScore(scoreRecordPO);
//        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
//        DailyTotalScoreDto scoreDto = new DailyTotalScoreDto();
//        scoreDto.setAccountId(accountId);
//        scoreDto.setName(accountPO.getNickName());
//        scoreDto.setScore(score);
//        scoreDto.setPortrait(accountPO.getPortrait());
//        return scoreDto;
//    }

    @Override
    public List<Top20Dto> findDailyTop20() throws UnsupportedEncodingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<DailyTotalScorePO> top20 = dailyTotalScorePersistenceService.findTop20(simpleDateFormat.format(new Date()));
        List<Top20Dto> top20Dtos = new ArrayList<>();
        if(top20 != null && top20.size()>0) {
            for(DailyTotalScorePO dailyTotalScorePO:top20) {
                Top20Dto top20Dto = new Top20Dto();
                top20Dto.setId(dailyTotalScorePO.getAccountId());
                top20Dto.setScore(dailyTotalScorePO.getScore());
                AccountPO accountPO = accountMapper.selectByPrimaryKey(dailyTotalScorePO.getAccountId());
                top20Dto.setName(URLDecoder.decode(accountPO.getNickName(),"utf-8"));
                top20Dto.setPortrat(accountPO.getPortrait());
                top20Dto.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                top20Dtos.add(top20Dto);
            }
        }
        return top20Dtos;
    }

    @Override
    public Boolean findIsThumbUp(Integer srcId, Integer toAccountId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("accountId", toAccountId);
        paramMap.put("srcId",srcId);
        return scoreRecordMapper.findIsThumbUp(paramMap);
    }

    @Override
    public Boolean findIsSignIn(Integer accountId) {
        return scoreRecordMapper.findIsSignIn(accountId);
    }

//    @Override
//    public TotalSignInDto findTotalSignIn(Integer accountId) {
//        List<Integer> days = new ArrayList<>();
//        List<Timestamp> totalSignIn = scoreRecordMapper.findTotalSignIn(accountId);
//        if(totalSignIn != null) {
//            Calendar calendar = Calendar.getInstance();
//            for(Timestamp timestamp:totalSignIn) {
//                calendar.setTime(timestamp);
//                days.add(calendar.get(Calendar.DAY_OF_MONTH));
//            }
//        }
//        return new TotalSignInDto(days,scoreRecordMapper.findIsSignIn(accountId));
//    }

    @Override
    public void updateConvertAward(Integer accountId, AwardTypeEnum awardType) {
        TotalScoreDto totalScoreDto = findTotalScore(accountId);
        Map<String,Integer> params = new HashMap<>();
        params.put("account_id",accountId);
        params.put("awardType",awardType.getCode());
        if(totalScoreDto.getScore()<awardType.findScore())
            throw new RuntimeException("积分不足");
        if(dailyAwardsMapper.findIsAwardToday(params))
            throw new RuntimeException("每种奖品只限兑换一次,您已经兑换过该奖品了");
        DailyAwardsPO dailyAwardsPO = dailyAwardsMapper.findByType(awardType.getCode());
        if(dailyAwardsPO == null)
            throw new RuntimeException("今天奖品已经领取完了～");
        dailyAwardsPO.setDel(true);
        dailyAwardsPO.setAccountId(accountId);
        dailyAwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if((awardType.getCode().equals(AwardTypeEnum.TEN_COUPON.getCode()) || awardType.getCode().equals(AwardTypeEnum.FIVE_COUPON.getCode())) && dailyAwardsMapper.find34Count()<20000) {
            DailyAwardsPO newDailyAwards = new DailyAwardsPO();
            newDailyAwards.setDel(false);
            newDailyAwards.setCreateTime(new Timestamp(System.currentTimeMillis()));
            newDailyAwards.setType(awardType);
            newDailyAwards.setCodeSecret("");
            newDailyAwards.setProdId(UUID.randomUUID().toString().replace("-", ""));
            dailyAwardsMapper.insertSelective(newDailyAwards);
        }
        dailyAwardsMapper.updateByPrimaryKeySelective(dailyAwardsPO);
        // TODO: 2018/1/16 插入总分表
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        TotalScorePO totalScorePO = new TotalScorePO(null, accountId, -1 * awardType.findScore(), accountPO.getProvince());
        totalScorePersistenceService.updateTotalScore(totalScorePO);
        // TODO: 2018/1/16 插入每日积分表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DailyTotalScorePO dailyTotalScorePO = new DailyTotalScorePO(null, accountId, -1 * awardType.findScore(), simpleDateFormat.format(new Date()), accountPO.getProvince(), null,null);
        dailyTotalScorePersistenceService.updateDailyTotalScore(dailyTotalScorePO);
    }

    @Override
    public List<MyAwardDto> findMyAward(Integer accountId) {
        DailyAwardsPO dailyAwardsParam = new DailyAwardsPO();
        dailyAwardsParam.setAccountId(accountId);
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
        Top20AwardsPO top20AwardsPO = top20AwardsMapper.findByAccountId(accountId);
        if(top20AwardsPO != null) {
            MyAwardDto myAwardDto = new MyAwardDto();
            myAwardDto.setAwardType(top20AwardsPO.getType());
            myAwardDto.setCreateTime(top20AwardsPO.getUpdateTime());
            myAwardDto.setIsReceivedAward(top20AwardsPO.getReceivedAward());
            if((System.currentTimeMillis()-top20AwardsPO.getUpdateTime().getTime()) >= 6*24*60*60*1000) {
                myAwardDto.setIsExpired(true);
            } else {
                myAwardDto.setIsExpired(false);
                myAwardDto.setCodeSecret(accountPersistenceService.findIsInfoComplete(accountId) ? top20AwardsPO.getCodeSecret() : "个人信息不完整");
            }
            myAwardDtos.add(myAwardDto);
        }
        Lucky20AwardsPO lucky20AwardsPO = lucky20AwardsMapper.findByAccountId(accountId);
        if(lucky20AwardsPO != null) {
            MyAwardDto myAwardDto = new MyAwardDto();
            myAwardDto.setAwardType(lucky20AwardsPO.getType());
            myAwardDto.setCreateTime(lucky20AwardsPO.getUpdateTime());
            myAwardDto.setIsReceivedAward(lucky20AwardsPO.getReceivedAward());
            if((System.currentTimeMillis()-lucky20AwardsPO.getUpdateTime().getTime()) >= 6*24*60*60*1000) {
                myAwardDto.setIsExpired(true);
            } else {
                myAwardDto.setCodeSecret(accountPersistenceService.findIsInfoComplete(accountId) ? lucky20AwardsPO.getCodeSecret() : "个人信息不完整");
            }
            myAwardDtos.add(myAwardDto);
        }
        for(MyAwardDto myAwardDto : myAwardDtos) {
            myAwardDto.setLastScore(lastScore);
        }
        return myAwardDtos;
    }

    @Override
    public List<AwardDto> findAllAwards(Integer accountId) {
//        DailyAwardsPO dailyAwardsParam = new DailyAwardsPO();
//        dailyAwardsParam.setAccountId(accountId);
       // List<DailyAwardsPO> dailyAwardsPOs = dailyAwardsMapper.select(dailyAwardsParam);
        Integer lastScore = findTotalScore(accountId).getScore();
//        if(dailyAwardsPOs!=null) {
//            for(DailyAwardsPO dailyAwardsPO:dailyAwardsPOs) {
//                lastScore = lastScore - dailyAwardsPO.getType().findScore();
//            }
//        }
        List<AwardDto> awardDtos = new ArrayList<>();
        AwardDto tencentAward = new AwardDto(AwardTypeEnum.TENCENT_VIP, dailyAwardsMapper.findByType(AwardTypeEnum.TENCENT_VIP.getCode())==null,AwardTypeEnum.TENCENT_VIP.findScore(),lastScore,dailyAwardsMapper.findCount(AwardTypeEnum.TENCENT_VIP.getCode()));
        AwardDto ofoAward = new AwardDto(AwardTypeEnum.OFO_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.OFO_COUPON.getCode())==null,AwardTypeEnum.OFO_COUPON.findScore(),lastScore,dailyAwardsMapper.findCount(AwardTypeEnum.OFO_COUPON.getCode()));
        AwardDto fiveAward = new AwardDto(AwardTypeEnum.FIVE_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.FIVE_COUPON.getCode())==null,AwardTypeEnum.FIVE_COUPON.findScore(),lastScore,dailyAwardsMapper.findCount(AwardTypeEnum.FIVE_COUPON.getCode()));
        AwardDto tenAward = new AwardDto(AwardTypeEnum.TEN_COUPON,dailyAwardsMapper.findByType(AwardTypeEnum.TEN_COUPON.getCode())==null,AwardTypeEnum.TEN_COUPON.findScore(),lastScore,dailyAwardsMapper.findCount(AwardTypeEnum.TEN_COUPON.getCode()));
        awardDtos.add(tencentAward);
        awardDtos.add(ofoAward);
        awardDtos.add(fiveAward);
        awardDtos.add(tenAward);

        return awardDtos;
    }

    @Override
    public String updateTop20AwardByAccountId(Integer accountId) {
        Top20AwardsPO top20AwardsPO = top20AwardsMapper.findByAccountId(accountId);
        if(!accountPersistenceService.findIsInfoComplete(accountId))
            throw new RuntimeException("个人信息不完整");
        if(top20AwardsPO != null ) {
            if((System.currentTimeMillis()-top20AwardsPO.getUpdateTime().getTime()) >= 6*24*60*60*1000L)
                throw new RuntimeException("已过期");
            top20AwardsMapper.updateByPrimaryKeySelective(top20AwardsPO);
            top20AwardsPO.setReceivedAward(true);
            top20AwardsPO.setDel(true);
            top20AwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        return top20AwardsPO == null?null:top20AwardsPO.getCodeSecret();
    }

    @Override
    public String updateLucky20AwardByAccountId(Integer accountId) {
        Lucky20AwardsPO lucky20AwardsPO = lucky20AwardsMapper.findByAccountId(accountId);
        if(!accountPersistenceService.findIsInfoComplete(accountId))
            throw new RuntimeException("个人信息不完整");
        if(lucky20AwardsPO != null) {
            if((System.currentTimeMillis()-lucky20AwardsPO.getUpdateTime().getTime())>=6*24*60*60*1000L)
                throw new RuntimeException("已过期");
            lucky20AwardsPO.setReceivedAward(true);
            lucky20AwardsPO.setDel(true);
            lucky20AwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            lucky20AwardsMapper.updateByPrimaryKeySelective(lucky20AwardsPO);
        }
        return lucky20AwardsPO == null?null:lucky20AwardsPO.getCodeSecret();
    }

    @Override
    public MySelfRankDto findSelfRank(Integer accountId) {
        Map<String,Object> map = findIsTop20(accountId);
        AccountPO accountPO = accountMapper.selectByPrimaryKey(accountId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int rank = dailyTotalScorePersistenceService.findRankByAccountId(accountId,simpleDateFormat.format(new Date()));
        DailyTotalScorePO dailyTotalScorePO = dailyTotalScorePersistenceService.findByAccountId(accountId,simpleDateFormat.format(new Date()));
        MySelfRankDto mySelfRankDto = new MySelfRankDto();

        ScoreRecordPO scoreRecordParam = new ScoreRecordPO();
        scoreRecordParam.setAccountId(accountId);
        if(map != null){
            mySelfRankDto.setRanking(-1);
        }else{
            mySelfRankDto.setRanking(rank);
        }

        mySelfRankDto.setTotalScore(dailyTotalScorePO==null?0:dailyTotalScorePO.getScore());
        mySelfRankDto.setNickName(accountPO.getNickName());
        mySelfRankDto.setPortrait(accountPO.getPortrait());
        return mySelfRankDto;
    }

    @Override
    public Map<String,Object> findIsTop20(Integer accountId) {
        Map<String,Object> resultMap = null;
        Top20AwardsPO top20AwardsPO = top20AwardsMapper.findByAccountId(accountId);
        if(top20AwardsPO != null) {
            resultMap = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            resultMap.put("type",top20AwardsPO.getType());
            resultMap.put("date",simpleDateFormat.format(top20AwardsPO.getUpdateTime()));
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> findTotalInfo(){

        Map<String,Object> totalInfo =new HashMap<>();
        Date currentTime = new Date(System.currentTimeMillis()-24*60*60*1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        String filePath="top20/"+dateString+".json";
        totalInfo=readObjectFromFile(filePath);
        if (totalInfo!=null && totalInfo.size()!=0){
            return totalInfo;
        }
        List<DailyTotalScorePO> scorePOS=dailyTotalScoreMapper.findTop20(dateString);
        List<Top20Dto> top20Dtos=new ArrayList<>();
        for (DailyTotalScorePO scorePO:scorePOS){
            AccountPO accountPO=accountMapper.selectByPrimaryKey(scorePO.getAccountId());
            Top20Dto top20Dto=new Top20Dto();
            try {
                top20Dto.setName(URLDecoder.decode(accountPO.getNickName(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            top20Dto.setPortrat(accountPO.getPortrait());
            top20Dto.setScore(scorePO.getScore());
            top20Dtos.add(top20Dto);
        }

        List<ProvinceInfoDto> provinceInfoDtos=dailyTotalScoreMapper.findPrivanceInfo(dateString);
        for (ProvinceInfoDto infoDto:provinceInfoDtos){
            infoDto.setProvince(ProvinceEnum.getEnum(Integer.parseInt(infoDto.getProvince())).getValue());
        }


        totalInfo.put("top20",top20Dtos);
        totalInfo.put("province",provinceInfoDtos);
        savetoFile("top20/"+dateString+".json",totalInfo);

        return totalInfo;
    }
    public void savetoFile(String filePath,Map<String,Object> obj){

        try {
           // FileWriter fw = new FileWriter(new File("/home/anicloud/third/files/"+filePath));
            FileOutputStream out = new FileOutputStream(new File("/home/anicloud/third/files/"+filePath));
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Map<String,Object> readObjectFromFile(String filePath)
    {
        Map<String,Object> objectMap=null;
        File file =new File("/home/anicloud/third/files/"+filePath);
        if (!file.exists()){
            return null;
        }
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            objectMap=(Map<String, Object>) objIn.readObject();

            objIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objectMap;
    }

}
