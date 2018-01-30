package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.Top20Dto;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.*;
import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.*;
import com.ani.ccyl.leg.persistence.service.facade.DailyTotalScorePersistenceService;
import com.ani.ccyl.leg.service.service.facade.ScoreRecordService;
import com.ani.ccyl.leg.service.service.facade.TimerTaskService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lihui on 17-12-27.
 */
@Service
public class TimerTaskServiceImpl implements TimerTaskService {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    @Autowired
    private DailyTotalScorePersistenceService dailyTotalScorePersistenceService;
    @Autowired
    private ScoreRecordService scoreRecordService;
    @Autowired
    private Top20AwardsMapper top20AwardsMapper;
    @Autowired
    private Lucky20AwardsMapper lucky20AwardsMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TotalScoreMapper totalScoreMapper;
    @Override
    public void updateDailyTop20() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000));
        List<DailyTotalScorePO> dailyTotalScorePOS = dailyTotalScorePersistenceService.findTop20(date);
        if(dailyTotalScorePOS != null) {
            int order = 1;
            for(DailyTotalScorePO top20PO:dailyTotalScorePOS) {
                Top20AwardsPO top20AwardsPO = top20AwardsMapper.findByType(AwardTypeEnum.getTopEnum(order).getCode());
                if(top20AwardsPO == null)
                    return;
                top20AwardsPO.setAccountId(top20PO.getAccountId());
                top20AwardsPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                top20AwardsPO.setDel(true);
                top20AwardsPO.setReceivedAward(true);
                if(top20PO.getAccountId()!=null) {
                    TotalScorePO totalScorePO = new TotalScorePO();
                    totalScorePO.setAccountId(top20PO.getAccountId());
                    List<TotalScorePO> scorePOS = totalScoreMapper.select(totalScorePO);
                    if(scorePOS != null) {
                        for(TotalScorePO totalScorePO1:scorePOS) {
                            totalScorePO1.setScore(0);
                            totalScoreMapper.updateByPrimaryKeySelective(totalScorePO1);
                        }
                    }
                }
                top20AwardsMapper.updateByPrimaryKeySelective(top20AwardsPO);
                order++;
            }
        }
    }

    @Override
//    @PostConstruct
    public void initTimeTask() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        Date date=calendar.getTime(); //第一次执行定时任务的时间
//        //如果第一次执行定时任务的时间 小于当前的时间
//        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
//        if (date.before(new Date())) {
//            date = this.addDay(date, 1);
//        }
//        Timer timer = new Timer();
//        MyTimerTask task = new MyTimerTask(this);
//        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
//        timer.schedule(task,date,PERIOD_DAY);
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

    @Override
    public void updateRunTask() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Top20AwardsPO> top20AwardsPOS = top20AwardsMapper.findByDate(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        if(top20AwardsPOS==null || top20AwardsPOS.size()==0) {
            this.updateDailyTop20();
            List<AccountPO> accountPOs = accountMapper.findNotInTop20(simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000)));
            List<AccountPO> luckyAccounts = new ArrayList<>();
            if (accountPOs != null && accountPOs.size() > 20) {
                HashSet<Integer> set = new HashSet<>();
                randomSet(accountPOs.size(), 20, set);
                for (Integer index : set) {
                    luckyAccounts.add(accountPOs.get(index));
                }
            } else if (accountPOs != null) {
                luckyAccounts = accountPOs;
            }
            this.insertLucky20(luckyAccounts);
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<AccountPO> accountPOs = accountMapper.findNotInTop20(simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000)));
            List<AccountPO> luckyAccounts = new ArrayList<>();
            if(accountPOs!=null && accountPOs.size()>20) {
                HashSet<Integer> set = new HashSet<>();
                randomSet(accountPOs.size()-1,20,set);
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

            if (set.size()<n){
                Random random = new Random();
                int num = random.nextInt(max);
                set.add(num);
            }else {
                break;
            }


        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(max, n, set);// 递归
        }
    }
    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
