package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.utils.LocationUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lihui on 17-12-29.
 */
public class TestLocation {
    @Test
    public void test() {
        LocationUtil.getAdd("116.3039","39.97646");
    }

    @Test
    public void testDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,17);
        calendar.set(Calendar.MINUTE,31);
        calendar.set(Calendar.SECOND,59);
        Date date = calendar.getTime();
        System.out.println(date.getTime());
        System.out.print(System.currentTimeMillis());
    }

    @Test
    public void testNickName() throws UnsupportedEncodingException {
        String nickName = URLEncoder.encode("lg985507","utf-8");
        System.out.print(nickName);
    }
}
