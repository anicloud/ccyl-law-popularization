package com.ani.ccyl.leg.persistence.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zhanglina on 18-1-26.
 */
@Table(name="t_shiro_session")
public class ShiroSessionPO implements Serializable {
    private static final long serialVersionUID = -6140956770780088509L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String sessionId;
    private byte[] sessionValue;
    private String shiroKey;
    private byte[] shiroValue;
    private Long createTime;


    public ShiroSessionPO() {

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

    public byte[] getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(byte[] sessionValue) {
        this.sessionValue = sessionValue;
    }

    public ShiroSessionPO(String sessionId, byte[] sessionValue, String shiroKey, byte[] shiroValue, Long createTime) {

        this.sessionId = sessionId;
        this.sessionValue = sessionValue;
        this.shiroKey = shiroKey;
        this.shiroValue = shiroValue;
        this.createTime = createTime;
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
