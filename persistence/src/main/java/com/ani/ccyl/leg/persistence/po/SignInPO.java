package com.ani.ccyl.leg.persistence.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "t_sign_in")
public class SignInPO implements Serializable{
    private static final long serialVersionUID = -6812858327425751336L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Timestamp updateTime;
    private Timestamp crateTime;
    private Boolean isDel;

    public SignInPO(Integer id, Integer accountId, Timestamp updateTime, Timestamp crateTime, Boolean isDel) {
        this.id = id;
        this.accountId = accountId;
        this.updateTime = updateTime;
        this.crateTime = crateTime;
        this.isDel = isDel;
    }

    public SignInPO() {
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Timestamp crateTime) {
        this.crateTime = crateTime;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
