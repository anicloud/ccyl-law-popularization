package com.ani.ccyl.leg.commons.enums;

/**
 * Created by lihui on 17-12-13.
 */
public enum ExceptionEnum {
    WECHAT_SERVER_TIME_OUT("公众号服务超时"),
    WECHAT_ERROR("公众号异常"),
    WECHAT_TOKEN_ERROR("token异常"),
    WECHAT_MAKE_MENU_ERROR("创建菜单失败"),
    WECHAT_PARSE_XML_ERROR("解析微信消息错误"),
    WECHAT_CHECK_SIGN_ERROR("验证签名异常"),
    WECHAT_GENERATE_SIGNATURE_ERROR("创建jssdk签名失败"),
    PARSE_EXCEL_ERROR("解析excel文件错误"),
    FILE_UPLOAD_EXCEPTION("上传文件失败"),
    FILE_TYPE_ERROR("文件类型错误"),
    SMS_SEND_EXCEPTION("短信发送失败"),
    SMS_VERIFY_EXCEPTION("短信验证异常");
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private ExceptionEnum(String value) {
        this.value = value;
    }
}
