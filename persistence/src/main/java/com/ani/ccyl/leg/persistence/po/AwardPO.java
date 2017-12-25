package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-22.
 */
@Table(name = "t_award")
public class AwardPO implements Serializable {
    private static final long serialVersionUID = -447829874719813690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer score;
    private AwardTypeEnum awardType;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public AwardPO() {
    }

    public AwardPO(Integer id, Integer accountId, Integer score, AwardTypeEnum awardType, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.awardType = awardType;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public AwardTypeEnum getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardTypeEnum awardType) {
        this.awardType = awardType;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
