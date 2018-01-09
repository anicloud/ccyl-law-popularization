package com.ani.ccyl.leg.commons.utils;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihui on 2018/1/6.
 */
public class IPUtil {
    public static String getRemoteAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    public static String callCmd(String[] cmd) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader (is);
            while ((line = br.readLine ()) != null) {
                result += line;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param cmd 第一个命令
     * @param another 第二个命令
     * @return  第二个命令的执行结果
     */
    public static String callCmd(String[] cmd,String[] another) {
        String result = "";
        String line = "";
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令
            proc = rt.exec(another);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine ()) != null) {
                result += line;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     *
     * @param ip 目标ip,一般在局域网内
     * @param sourceString 命令处理的结果字符串
     * @param macSeparator mac分隔符号
     * @return mac地址，用上面的分隔符号表示
     */
    public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) {
        String result = "";
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        while(matcher.find()){
            result = matcher.group(1);
            if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
                break; //如果有多个IP,只匹配本IP对应的Mac.
            }
        }

        return result;
    }



    /**
     *
     * @param ip 目标ip
     * @return  Mac Address
     *
     */
    public static String getMacInWindows(final String ip){
        String result = "";
        String[] cmd = {
                "cmd",
                "/c",
                "ping " + ip
        };
        String[] another = {
                "cmd",
                "/c",
                "arp -a"
        };

        String cmdResult = callCmd(cmd,another);
        result = filterMacAddress(ip,cmdResult,"-");

        return result;
    }

    /**
     * @param ip 目标ip
     * @return  Mac Address
     *
     */
    public static String getMacInLinux(final String ip){
        String result = "";
        String[] cmd = {
                "/bin/sh",
                "-c",
                "ping " + ip + " -c 2 && arp -a"
        };
        String cmdResult = callCmd(cmd);
        result = filterMacAddress(ip,cmdResult,":");

        return result;
    }
    public static String getPhoneMACAddress(String ip) {
        String str="";
        String macAddress="";
        try {

            System.out.println(ip);
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            System.out.println("===process=="+p);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());

            BufferedReader br = new BufferedReader(ir);

            while ((str = br.readLine()) != null) {
            if(str.indexOf("MAC")>1){
            macAddress = str.substring(str.indexOf("MAC")+9, str.length());
            macAddress = macAddress.trim();
            System.out.println("macAddress:" + macAddress);
            break;
            }
        }
        p.destroy();
        br.close();
        ir.close();
        } catch (IOException ex) {

        }
        return macAddress;
    }

    /**
     * 获取MAC地址
     * @return 返回MAC地址
     */
    public static String getMACAddress(String ip){
        String macAddress = "";
        macAddress = getMacInWindows(ip).trim();
        if(macAddress==null||"".equals(macAddress)){
            macAddress = getMacInLinux(ip).trim();
            return macAddress;
        }
        if (macAddress ==null||"".equals(macAddress)){
            macAddress = getPhoneMACAddress(ip).trim();
            return macAddress;
        }

        return macAddress;
    }

}
