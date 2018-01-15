package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_daily_total_score")
public class DailyTotalScorePO implements Serializable {
    private static final long serialVersionUID = -8336600718980460409L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer score;
    private String date;
    private ProvinceEnum province;
    private Integer questionTime;

    public DailyTotalScorePO() {
    }

    public DailyTotalScorePO(Integer id, Integer accountId, Integer score, String date, ProvinceEnum province, Integer questionTime) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.date = date;
        this.province = province;
        this.questionTime = questionTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProvinceEnum getProvince() {
        return province;
    }

    public void setProvince(ProvinceEnum province) {
        this.province = province;
    }

    public Integer getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Integer questionTime) {
        this.questionTime = questionTime;
    }
}
