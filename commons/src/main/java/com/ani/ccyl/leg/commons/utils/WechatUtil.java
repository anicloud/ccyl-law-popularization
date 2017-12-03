package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.wechat.*;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * Created by lihui on 17-12-3.
 */
public class WechatUtil {
    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = Constants.PROPERTIES.getProperty("wechat.access.token.url");
    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = Constants.PROPERTIES.getProperty("wechat.menu.create.url");
    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            System.out.println(buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.err.println("https request error:{}");
//            log.error("https request error:{}", e);
        }
        return jsonObject;
    }
    /**
     * 获取access_token
     *
     * @param appId 凭证
     * @param appSecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appId, String appSecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (Exception e) {
                accessToken = null;
                // 获取token失败
                System.out.println("获取token失败 errcode:"+jsonObject.getInt("errcode")+"errmsg:"+jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }
    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                System.out.println("创建菜单失败errcode:"+jsonObject.getInt("errcode")+"errmsg:"+jsonObject.getString("errmsg"));
//	            log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    public static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("个人信息查看");
        btn11.setType("click");
        btn11.setKey("stuInfoView");

        CommonButton btn12 = new CommonButton();
        btn12.setName("个人信息修改");
        btn12.setType("click");
        btn12.setKey("stuInfoEdit");

        CommonButton btn21 = new CommonButton();
        btn21.setName("行程查看");
        btn21.setType("click");
        btn21.setKey("stuTravelView");

        CommonButton btn22 = new CommonButton();
        btn22.setName("行程添加");
        btn22.setType("click");
        btn22.setKey("stuTravelAdd");

        CommonButton btn23 = new CommonButton();
        btn23.setName("行程修改");
        btn23.setType("click");
        btn23.setKey("stuTravelEdit");

        CommonButton btn31 = new CommonButton();
        btn31.setName("操作说明");
        btn31.setType("click");
        btn31.setKey("help");

        CommonButton btn32 = new CommonButton();
        btn32.setName("呼叫管理员");
        btn32.setType("click");
        btn32.setKey("callAdmin");

        CommonButton btn33 = new CommonButton();
        btn33.setName("意见反馈");
        btn33.setType("click");
        btn33.setKey("suggestions");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("个人信息");
        mainBtn1.setSub_button(new Button[] { btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("行程");
        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("帮助");
        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

        /**
         * 在某个一级菜单下没有二级菜单的情况，menu应如下定义<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}
