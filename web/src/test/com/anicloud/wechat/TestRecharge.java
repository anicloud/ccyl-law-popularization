package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.utils.RechargeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by lihui on 17-12-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class TestRecharge {
    @Test
    public void testRecharge() {
        RechargeUtil.reCharge("15731118087",1, UUID.randomUUID().toString());
    }
}
