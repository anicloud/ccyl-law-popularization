package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.utils.SMSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lihui on 17-12-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class TestSMS {
    @Test
    public void sendSMS() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SMSUtil.sendSMSCode();
    }

    @Test
    public void verifyCode() {
        SMSUtil.verifyCode("15731118087","387943");
    }
}
