package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_total_score")
public class TotalScorePO implements Serializable {
    private static final long serialVersionUID = -7902800833020882787L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer score;
    private ProvinceEnum province;

    public TotalScorePO(Integer id, Integer accountId, Integer score, ProvinceEnum province) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.province = province;
    }

    public TotalScorePO() {
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

    public ProvinceEnum getProvince() {
        return province;
    }

    public void setProvince(ProvinceEnum province) {
        this.province = province;
    }
}
