package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "t_total_top20_awards")
public class TotalTop20AwardsPO implements Serializable {
    private static final long serialVersionUID = 1492027837181854016L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String prodId;
    private String codeSecret;
    private AwardTypeEnum type;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public TotalTop20AwardsPO(Integer id, String prodId, String codeSecret, AwardTypeEnum type, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.prodId = prodId;
        this.codeSecret = codeSecret;
        this.type = type;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public TotalTop20AwardsPO() {
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
