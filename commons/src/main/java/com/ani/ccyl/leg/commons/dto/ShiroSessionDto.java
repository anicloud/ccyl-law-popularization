package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

/**
 * Created by zhanglina on 18-1-26.
 */
public class ShiroSessionDto implements Serializable{

    private static final long serialVersionUID = 6393014115817944060L;
    private Integer id;
    private String sessionId;
    private byte[] session;
    private String shiroKey;
    private byte[] shiroValue;
    private Long createTime;

    public ShiroSessionDto(Integer id, String sessionId, byte[] session, String shiroKey, byte[] shiroValue, Long createTime) {
        this.id = id;
        this.sessionId = sessionId;
        this.session = session;
        this.shiroKey = shiroKey;
        this.shiroValue = shiroValue;
        this.createTime = createTime;
    }

    public ShiroSessionDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public byte[] getSession() {
        return session;
    }

    public void setSession(byte[] session) {
        this.session = session;
    }

    public String getShiroKey() {
        return shiroKey;
    }

    public void setShiroKey(String shiroKey) {
        this.shiroKey = shiroKey;
    }

    public byte[] getShiroValue() {
        return shiroValue;
    }

    public void setShiroValue(byte[] shiroValue) {
        this.shiroValue = shiroValue;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
