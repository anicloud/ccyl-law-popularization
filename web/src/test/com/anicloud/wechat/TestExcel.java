package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.dto.TotalAwardsDto;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import com.ani.ccyl.leg.persistence.mapper.TotalDailyAwardsMapper;
import com.ani.ccyl.leg.persistence.mapper.TotalLucky20AwardsMapper;
import com.ani.ccyl.leg.persistence.mapper.TotalTop20AwardsMapper;
import com.ani.ccyl.leg.persistence.po.TotalLucky20AwardsPO;
import com.ani.ccyl.leg.persistence.po.TotalTop20AwardsPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class TestExcel {
    @Autowired
    private TotalLucky20AwardsMapper totalLucky20AwardsMapper;
    @Autowired
    private TotalDailyAwardsMapper  totalDailyAwardsMapper;
    @Autowired
    private TotalTop20AwardsMapper totalTop20AwardsMapper;
    @Test
    public void testReadChoice() {
        List<QuestionDto> questionDtos = ExcelUtil.readFromExcel(QuestionTypeEnum.CHOICE, "/home/lihui/choice.xlsx", null);
        System.out.print(questionDtos.size());
    }

    @Test
    public void testReadJudge() {
        List<QuestionDto> questionDtos = ExcelUtil.readFromExcel(QuestionTypeEnum.JUDGEMENT,"/home/lihui/judge.xlsx",null);
        System.out.print(questionDtos.size());
    }

    @Test
    public void testReadAward() {
        String basePath = "C:\\Users\\lihui\\Downloads\\award\\award\\";
        String top1Path = basePath+"jd100.xlsx";
        String top2Path = basePath+"jd50.xls";
        String top3Path = basePath+"jd30.xlsx";
        String top4sPath = basePath+"tencenttop20.xls";
        String luckyPath1 = basePath+"mobike5-1.xls";
        String luckyPath2 = basePath+"mobike5-2.xls";
        String convert1Path = basePath+"tencent.xls";
        String convert2Path = basePath+"mobike10.xls";
        List<TotalAwardsDto> top1 = ExcelUtil.readAwardsFromExcel(top1Path, AwardTypeEnum.TOP_1);
        for(TotalAwardsDto totalAwardsDto:top1) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> top2 = ExcelUtil.readAwardsFromExcel(top2Path,AwardTypeEnum.TOP_2);
        for(TotalAwardsDto totalAwardsDto:top2) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> top3 = ExcelUtil.readAwardsFromExcel(top3Path,AwardTypeEnum.TOP_3);
        for(TotalAwardsDto totalAwardsDto:top3) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> top4s = ExcelUtil.readAwardsFromExcel(top4sPath,AwardTypeEnum.TOP_4S);
        for(TotalAwardsDto totalAwardsDto:top4s) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> lucky = ExcelUtil.readAwardsFromExcel(luckyPath1,AwardTypeEnum.LUCKY);
        lucky.addAll(ExcelUtil.readAwardsFromExcel(luckyPath2,AwardTypeEnum.LUCKY));
        for(TotalAwardsDto totalAwardsDto:lucky) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> conver1 = ExcelUtil.readAwardsFromExcel(convert1Path,AwardTypeEnum.TENCENT_VIP);
        for(TotalAwardsDto totalAwardsDto:conver1) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }
        List<TotalAwardsDto> convert2 = ExcelUtil.readAwardsFromExcel(convert2Path,AwardTypeEnum.OFO_COUPON);
        for(TotalAwardsDto totalAwardsDto:convert2) {
            TotalTop20AwardsPO totalTop20AwardsPO = new TotalTop20AwardsPO(null,totalAwardsDto.getProdId(),totalAwardsDto.getCodeSecret(),totalAwardsDto.getType(),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),false);
            totalTop20AwardsMapper.insertSelective(totalTop20AwardsPO);
        }

    }
}
