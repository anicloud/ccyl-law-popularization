package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihui on 17-12-25.
 */
public class RechargeUtil {
    private static String apiKey = Constants.PROPERTIES.getProperty("ihuyi.recharge.url");
    private static String apiId = Constants.PROPERTIES.getProperty("ihuyi.api.id");
    public static void reCharge(String phone, Integer price, String orderId) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String timestamp = simpleDateFormat.format(new Date());
            String sign = getSign(phone,orderId,price,timestamp);
            SSLClient httpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(Constants.PROPERTIES.getProperty("ihuyi.recharge.url"));
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("action", "recharge"));
            nvps.add(new BasicNameValuePair("username", apiKey));
            nvps.add(new BasicNameValuePair("mobile", phone));
            nvps.add(new BasicNameValuePair("package",String.valueOf(price)));
            nvps.add(new BasicNameValuePair("orderid",orderId));
            nvps.add(new BasicNameValuePair("timestamp",timestamp));
            nvps.add(new BasicNameValuePair("sign",sign));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("充值失败");
        }
    }
    public static String getSign(String phone, String orderId, Integer price, String timestamp) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String srcStr = "apikey="+apiKey+"&mobile="+phone+"&orderid="+orderId+"&package="+price+"&timestamp="+timestamp+"&username="+apiId;
        messageDigest.update(srcStr.getBytes());
        byte bytes[] = messageDigest.digest();
        return WechatUtil.byteToStr(bytes).toLowerCase();
    }
}
