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
@Table(name = "t_achievement")
public class AchievementPO implements Serializable {
    private static final long serialVersionUID = -5403218849722249032L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer questionId;
    private Boolean isCorrect;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public AchievementPO(Integer id, Integer accountId, Integer questionId, Boolean isCorrect, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.accountId = accountId;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public AchievementPO() {
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
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
