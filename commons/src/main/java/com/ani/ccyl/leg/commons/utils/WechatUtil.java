package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.AccessTokenDto;
import com.ani.ccyl.leg.commons.dto.wechat.*;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.exception.WechatException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by lihui on 17-12-3.
 */
public class WechatUtil {
    // 获取access_token的接口地址（GET） 限2000（次/天）
    public final static String access_token_url = Constants.PROPERTIES.getProperty("wechat.access.token.url");
    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = Constants.PROPERTIES.getProperty("wechat.menu.create.url");
    // 与接口配置信息中的Token要一致
    private static String token = Constants.PROPERTIES.getProperty("wechat.token");

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
            inputStream.close();
            httpUrlConn.disconnect();
            System.out.println(buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
            throw new WechatException(ce.getMessage(), ExceptionEnum.WECHAT_SERVER_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WechatException(e.getMessage(),ExceptionEnum.WECHAT_ERROR);
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
    public static AccessTokenDto getAccessToken(String appId, String appSecret) {
        AccessTokenDto accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessTokenDto();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setTokenExpiresIn(jsonObject.getLong("expires_in"));
                accessToken.setTokenCreateTime(new Timestamp(System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new WechatException("获取token失败",ExceptionEnum.WECHAT_TOKEN_ERROR);
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
                throw new WechatException("创建菜单失败",ExceptionEnum.WECHAT_MAKE_MENU_ERROR);
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

        ViewButton btn33 = new ViewButton();
        btn33.setName("授权");
        btn33.setType("view");
        btn33.setKey("suggestions");
        btn33.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constants.PROPERTIES.getProperty("wechat.appid")+"&redirect_uri="+Constants.PROPERTIES.getProperty("wechat.redirect.url")+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
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
         * 比如，第三个一级菜单项不是多级菜单，而直接是单级菜单，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
    public static ReceiveXmlEntity getMsgEntity(HttpServletRequest request)  {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            return getMsgEntity(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new WechatException("解析微信消息错误",ExceptionEnum.WECHAT_PARSE_XML_ERROR);
        }
    }

    public static ReceiveXmlEntity getMsgEntity(String strXml) {
        try {
            ReceiveXmlEntity msg = null;
            //<xml>    <URL><![CDATA[http://84799e25.ngrok.io/leg/test/helloWorld]]></URL>    <ToUserName><![CDATA[lh812486664]]></ToUserName>    <FromUserName><![CDATA[test]]></FromUserName>    <CreateTime>1</CreateTime>    <MsgType><![CDATA[text]]></MsgType>    <Content><![CDATA[testmsg]]></Content>    <MsgId>123</MsgId>    <Encrypt><![CDATA[5i7mxHYEmuQqQvZtXhlobdKgZFnWrv62K0MPDlnUZfkJUY+SGtDFtYXfkWFppHxiIz3uEwvP7If98gWyqgYa7qG6MBKd+ftzXGJq92uJSg9ZM9BgrFw5259ZOa68eWgHH9Imko05eqhVB7NZBlFqb1QWcD9Jp0r4jf9SdZdbVplO7XaZrEqhXOSC3yfz4hJWd6Q124qWXQdaLQNwt6ta2Y/BsGuwb9g7Go9Eht/Rt2GR+RQFJa3PG84XNGiwA0Xflgzl6JPy8efgbFjDvf8er0oiRJbxWt5mSTtu1DuCesS4YiFJVXUXqGLVHvx57NqbwP5wvRFZMHT5FK+8y7VR4BjuBwnpaXQtao+Fw08F3qvoVjvvxjRY4LzUowy4PXuYH3eUoTuuTFtx0JBmUEHacHcYoRaG67gyxOhrFAMzA1O9poczja0sMj277uaFOdJSXQNWVItqH35bm0GrE1GP8g==]]></Encrypt></xml>
            if (strXml == null || strXml.length() <= 0)
                return null;

            // 将字符串转化为XML文档对象
            Document document = DocumentHelper.parseText(strXml);
            // 获得文档的根节点
            Element root = document.getRootElement();
            // 遍历根节点下所有子节点
            Iterator<?> iter = root.elementIterator();

            // 遍历所有结点
            Class<?> c = Class.forName("com.ani.ccyl.leg.commons.dto.wechat.ReceiveXmlEntity");
            msg = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象

            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                Field field = c.getDeclaredField(ele.getName());
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
                method.invoke(msg, ele.getText());
            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WechatException("解析微信消息错误",ExceptionEnum.WECHAT_PARSE_XML_ERROR);
        }
    }

    /**
     * 验证签名</br>
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new WechatException("解析签名异常",ExceptionEnum.WECHAT_CHECK_SIGN_ERROR);
        }

        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }
    public static String getJsSDKSign(String noncestr,String jsapi_ticket, String timestamp, String url) {
        if(StringUtils.isEmpty(jsapi_ticket))
            throw new WechatException("创建签名失败：ticket为空",ExceptionEnum.WECHAT_GENERATE_SIGNATURE_ERROR);
        try {
            StringBuilder content = new StringBuilder();
            content.append("jsapi_ticket=").append(jsapi_ticket).append("&noncestr=").append(noncestr).append("&timestamp=").append(timestamp).append("&url=").append(url);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] digest = messageDigest.digest(content.toString().getBytes());
            return byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new WechatException("创建jssdk签名失败",ExceptionEnum.WECHAT_GENERATE_SIGNATURE_ERROR);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串</br>
     * @param byteArray
     * @return
     * @throws
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串</br>
     * @param mByte
     * @return
     * @throws
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 响应消息转换成xml返回
     * 文本消息对象转换成xml
     */
    public  static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    /**
     * 音乐消息的对象的转换成xml
     *
     */
    public  static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }
    /**
     * 图文消息的对象转换成xml
     *
     */
    public  static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(newsMessage);
    }
    /**
     * 拓展xstream，使得支持CDATA块
     *
     */
    private static XStream xstream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                //对所有的xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });

}
