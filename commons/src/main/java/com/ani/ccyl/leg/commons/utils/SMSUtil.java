package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.exception.SMSException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-12-15.
 */
public class SMSUtil {
    //发送验证码的请求路径URL
    private static final String SERVER_URL=Constants.PROPERTIES.getProperty("netease.server.url");
    private static final String VERIFY_URL=Constants.PROPERTIES.getProperty("netease.verify.url");
    private static final String APP_KEY=Constants.PROPERTIES.getProperty("netease.app.key");
    private static final String APP_SECRET=Constants.PROPERTIES.getProperty("netease.app.secret");
    //随机数
    private static final String NONCE=Constants.PROPERTIES.getProperty("netease.nonce");
    //短信模板ID
    private static final String TEMPLATEID=Constants.PROPERTIES.getProperty("netease.template.id");
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN= Constants.PROPERTIES.getProperty("netease.code.length");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static void sendSMSCode(String phone) {
        try {
            SSLClient httpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(SERVER_URL);
            String curTime = String.valueOf((new Date()).getTime() / 1000L);

            String checkSum = getCheckSum(APP_SECRET, NONCE, curTime);

            httpPost.addHeader("AppKey", APP_KEY);
            httpPost.addHeader("Nonce", NONCE);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
            nvps.add(new BasicNameValuePair("mobile", phone));
            nvps.add(new BasicNameValuePair("codeLen", CODELEN));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch(Exception e) {
            e.printStackTrace();
            throw new SMSException("短信发送失败",ExceptionEnum.SMS_SEND_EXCEPTION);
        }
    }

    public static Boolean verifyCode(String phone, String code) {
        try {
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            SSLClient httpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(VERIFY_URL);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpPost.addHeader("AppKey", APP_KEY);
            httpPost.addHeader("Nonce", NONCE);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", getCheckSum(APP_SECRET,NONCE,curTime));
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("mobile",phone));
            nameValuePairs.add(new BasicNameValuePair("code",code));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String jsonResult = EntityUtils.toString(response.getEntity(), "utf-8");
            Map<String,String> resultMap = OBJECT_MAPPER.readValue(jsonResult, Map.class);
            if(resultMap.get("code").equals("200"))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SMSException("验证异常",ExceptionEnum.SMS_VERIFY_EXCEPTION);
        }
    }

    private static String getCheckSum(String APP_SECRET, String NONCE, String curTime) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] digest = messageDigest.digest((APP_SECRET + NONCE + curTime).getBytes());
        return WechatUtil.byteToStr(digest).toLowerCase();
    }
}
