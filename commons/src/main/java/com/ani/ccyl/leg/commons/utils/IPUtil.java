package com.ani.ccyl.leg.commons.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by lihui on 2018/1/6.
 */
public class IPUtil {
    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getMACAddress(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(
                                str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("获取mac地址失败："+e.getMessage());
        }
        return macAddress;
    }
}
