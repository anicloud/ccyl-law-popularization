package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.utils.LocationUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000));
        System.out.print(date);
    }
}
