package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.persistence.mapper.DailyTop20Mapper;
import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
import com.ani.ccyl.leg.persistence.po.DailyTop20PO;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;
import com.ani.ccyl.leg.service.service.facade.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lihui on 17-12-27.
 */
@Service
public class TimerTaskServiceImpl implements TimerTaskService {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Autowired
    private DailyTop20Mapper dailyTop20Mapper;
    @Override
    public void updateDailyTop20() {
        List<ScoreRecordPO> dailyTop20 = scoreRecordMapper.findDailyTop20(new Timestamp(System.currentTimeMillis()-24*60*60*1000L));
        if(dailyTop20 != null) {
            int index = 1;
            for(ScoreRecordPO scoreRecordPO:dailyTop20) {
                DailyTop20PO dailyTop20PO = new DailyTop20PO();
                dailyTop20PO.setAccountId(scoreRecordPO.getAccountId());
                if(dailyTop20Mapper.selectCount(dailyTop20PO)==0) {
                    dailyTop20PO.setOrderNum(index);
                    dailyTop20PO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    dailyTop20Mapper.insertSelective(dailyTop20PO);
                    index++;
                    // TODO: 17-12-27 向用户推送中奖消息
                    // TODO: 17-12-27 发送奖品
                }
            }
        }
    }

    @Override
    @PostConstruct
    public void initTimeTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime(); //第一次执行定时任务的时间
        //如果第一次执行定时任务的时间 小于当前的时间
        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            date = this.addDay(date, 1);
        }
        Timer timer = new Timer();
        MyTimerTask task = new MyTimerTask(this);
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task,date,PERIOD_DAY);
    }
    private class MyTimerTask extends TimerTask {
        private TimerTaskService timerTaskService;
        MyTimerTask(TimerTaskService timerTaskService) {
            this.timerTaskService = timerTaskService;
        }

        @Override
        public void run() {
            timerTaskService.updateDailyTop20();
            // TODO: 17-12-27 从未入选前20的用户（即accountId不在t_daily_top20中）中 抽取20名幸运奖
        }
    }
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
