package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.utils.IPUtil;
import org.junit.Test;

/**
 * Created by lihui on 2018/1/6.
 */
public class TestMac {
    @Test
    public void testMac() {
        String macAddress = IPUtil.getMACAddress("114.242.250.23");
        System.out.print(macAddress);
    }
}
