package com.ani.ccyl.leg;

import com.ani.ccyl.leg.service.service.facade.TimerTaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhanglina on 18-1-17.
 */
public class MyTest {
    @Autowired
    TimerTaskService timerTaskService;
    @Test
    public void testi(){
        //timerTaskService.top20ToJson();
    }

}
