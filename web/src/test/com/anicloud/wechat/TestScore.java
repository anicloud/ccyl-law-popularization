package com.anicloud.wechat;

import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lihui on 2017/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class TestScore {
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Test
    public void testScore() {
        ScoreRecordPO scoreRecordPO = new ScoreRecordPO();
        scoreRecordPO.setAccountId(1);
        scoreRecordPO.setSrcAccountId(1);
        List<ScoreRecordPO> byConditions = scoreRecordMapper.findByConditions(scoreRecordPO);
        System.out.print(byConditions);
    }
}
