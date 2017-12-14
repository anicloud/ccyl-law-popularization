package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-13.
 */
public class JsSDKConfigDto implements Serializable {
    private static final long serialVersionUID = -2471319734054936073L;
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;

    public JsSDKConfigDto(String appId, String timestamp, String nonceStr, String signature) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

    public JsSDKConfigDto() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
