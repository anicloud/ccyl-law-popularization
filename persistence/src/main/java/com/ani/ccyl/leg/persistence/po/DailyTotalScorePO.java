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
    private String logDate;
    private ProvinceEnum province;
    private Integer questionTime;
    private Integer correctCount;
    private Integer questionId;

    public DailyTotalScorePO() {
    }

    public DailyTotalScorePO(Integer id, Integer accountId, Integer score, String logDate, ProvinceEnum province, Integer questionTime,Integer correctCount) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.logDate = logDate;
        this.province = province;
        this.questionTime = questionTime;
        this.correctCount = correctCount;
    }

    public DailyTotalScorePO(Integer accountId, Integer score, String logDate, ProvinceEnum province, Integer questionTime, Integer correctCount, Integer questionId) {
        this.accountId = accountId;
        this.score = score;
        this.logDate = logDate;
        this.province = province;
        this.questionTime = questionTime;
        this.correctCount = correctCount;
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
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

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String date) {
        this.logDate = date;
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
