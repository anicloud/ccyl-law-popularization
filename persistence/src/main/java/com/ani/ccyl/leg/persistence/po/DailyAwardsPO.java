package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-28.
 */
@Table(name = "t_daily_awards")
public class DailyAwardsPO implements Serializable {
    private static final long serialVersionUID = 2373235413065666238L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String prodId;
    private String codeSecret;
    private AwardTypeEnum type;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public DailyAwardsPO() {
    }

    public DailyAwardsPO(Integer id, String prodId, String codeSecret, AwardTypeEnum type, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.prodId = prodId;
        this.codeSecret = codeSecret;
        this.type = type;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public AwardTypeEnum getType() {
        return type;
    }

    public void setType(AwardTypeEnum type) {
        this.type = type;
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
