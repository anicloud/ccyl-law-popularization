package com.ani.ccyl.leg.commons.dto.wechat;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-3.
 */
public class AccessToken implements Serializable{
    private static final long serialVersionUID = -5803094116817794462L;
    private String token;
    private int expiresIn;

    public AccessToken() {
    }

    public AccessToken(String token, int expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
