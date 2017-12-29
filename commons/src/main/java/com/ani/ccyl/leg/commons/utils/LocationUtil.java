package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.net.URL;

public class LocationUtil {
    public static ProvinceEnum getAdd(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
            JSONObject jsonObject = JSONObject.fromObject(res);
            JSONArray addrList = jsonObject.getJSONArray("addrList");
            if(addrList.size()>0) {
                JSONObject addr = addrList.getJSONObject(0);
                String admName = addr.getString("admName");
                if(!StringUtils.isEmpty(admName)) {
                    String[] names = admName.split(",");
                    if(names.length==3) {
                        return ProvinceEnum.getEnum(names[0]);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询地址异常");
        }
    }
}
