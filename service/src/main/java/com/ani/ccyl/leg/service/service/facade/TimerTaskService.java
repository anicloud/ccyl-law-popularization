package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.persistence.po.AccountPO;

import java.util.List;

/**
 * Created by lihui on 17-12-27.
 */
public interface TimerTaskService {
    void updateDailyTop20();
    void initTimeTask();
    void insertLucky20(List<AccountPO> lucky20POs);

    void top20ToJson();
    void provinceRankToJson();
}
