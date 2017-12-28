package com.ani.ccyl.leg.persistence.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-27.
 */
@Table(name = "t_daily_top20")
public class DailyTop20PO implements Serializable {
    private static final long serialVersionUID = -7251725225567271800L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer orderNum;
    private Boolean isReceiveAward;
    private String codeSecret;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public DailyTop20PO(Integer id, Integer accountId, Integer orderNum, Boolean isReceiveAward, String codeSecret, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.accountId = accountId;
        this.orderNum = orderNum;
        this.isReceiveAward = isReceiveAward;
        this.codeSecret = codeSecret;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public Boolean getReceiveAward() {
        return isReceiveAward;
    }

    public void setReceiveAward(Boolean receiveAward) {
        isReceiveAward = receiveAward;
    }

    public DailyTop20PO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
