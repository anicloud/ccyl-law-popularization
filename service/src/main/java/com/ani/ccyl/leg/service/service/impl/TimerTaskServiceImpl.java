package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.*;
import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.*;
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
    private DailyAwardsMapper dailyAwardsMapper;
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Autowired
    private Top20AwardsMapper top20AwardsMapper;
    @Autowired
    private Lucky20AwardsMapper lucky20AwardsMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void updateDailyTop20() {
        List<ScoreRecordPO> dailyTop20 = scoreRecordMapper.findDailyTop20(new Timestamp(System.currentTimeMillis()-24*60*60*1000L));
        if(dailyTop20 != null) {
            int order = 1;
            for(ScoreRecordPO scoreRecordPO:dailyTop20) {
                Top20AwardsPO top20AwardsPO = top20AwardsMapper.findByType(AwardTypeEnum.getTopEnum(order).getCode());
                top20AwardsPO.setAccountId(scoreRecordPO.getAccountId());
                top20AwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                top20AwardsPO.setDel(true);
                top20AwardsPO.setType(AwardTypeEnum.getTopEnum(order));
                top20AwardsPO.setReceivedAward(false);
                /*积分清零功能*/
                DailyAwardsPO dailyAwardsParam = new DailyAwardsPO();
                dailyAwardsParam.setAccountId(scoreRecordPO.getAccountId());
                List<DailyAwardsPO> dailyAwardsPOs = dailyAwardsMapper.select(dailyAwardsParam);
                Integer residueScore = 0;
                if(dailyAwardsPOs!=null) {
                    for (DailyAwardsPO dailyAwardsPO : dailyAwardsPOs) {
                        residueScore = residueScore + dailyAwardsPO.getType().findScore();
                    }
                }

                /*积分清零功能结束*/
                top20AwardsMapper.updateByPrimaryKeySelective(top20AwardsPO);
                order++;
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
    @Override
    public void insertLucky20(List<AccountPO> lucky20POs) {
        for(AccountPO accountPO:lucky20POs) {
            Lucky20AwardsPO lucky20AwardsPO = lucky20AwardsMapper.findNewOne();
            lucky20AwardsPO.setAccountId(accountPO.getId());
            lucky20AwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            lucky20AwardsPO.setDel(true);
            lucky20AwardsPO.setType(AwardTypeEnum.LUCKY);
            lucky20AwardsPO.setReceivedAward(false);
            lucky20AwardsMapper.updateByPrimaryKeySelective(lucky20AwardsPO);
        }
    }

    private class MyTimerTask extends TimerTask {
        private TimerTaskService timerTaskService;
        MyTimerTask(TimerTaskService timerTaskService) {
            this.timerTaskService = timerTaskService;
        }

        @Override
        public void run() {
            timerTaskService.updateDailyTop20();
            List<AccountPO> accountPOs = accountMapper.findNotInTop20();
            List<AccountPO> luckyAccounts = new ArrayList<>();
            if(accountPOs!=null && accountPOs.size()>20) {
                HashSet<Integer> set = new HashSet<>();
                randomSet(accountPOs.size(),20,set);
                for(Integer index:set) {
                    luckyAccounts.add(accountPOs.get(index));
                }
            } else if (accountPOs != null){
                luckyAccounts = accountPOs;
            }
            timerTaskService.insertLucky20(luckyAccounts);
        }
    }

    public static void randomSet(int max, int n, HashSet<Integer> set) {
        for (int i = 0; i < n; i++) {
            Random random = new Random(max);
            int num = random.nextInt();
            set.add(num);
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(max, n - setSize, set);// 递归
        }
    }
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
