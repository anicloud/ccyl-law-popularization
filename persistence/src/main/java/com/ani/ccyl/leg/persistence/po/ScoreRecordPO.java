package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "t_score_record")
public class ScoreRecordPO implements Serializable {
    private static final long serialVersionUID = -3922003186472664899L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer score;
    private ScoreSrcTypeEnum srcType;
    private Integer srcQuestionId;
    private String selfAnswer;
    private Integer srcAccountId;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public ScoreRecordPO(Integer id, Integer accountId, Integer score, ScoreSrcTypeEnum srcType, Integer srcQuestionId, String selfAnswer, Integer srcAccountId, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.srcType = srcType;
        this.srcQuestionId = srcQuestionId;
        this.selfAnswer = selfAnswer;
        this.srcAccountId = srcAccountId;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public String getSelfAnswer() {
        return selfAnswer;
    }

    public void setSelfAnswer(String selfAnswer) {
        this.selfAnswer = selfAnswer;
    }

    public Integer getSrcAccountId() {
        return srcAccountId;
    }

    public void setSrcAccountId(Integer srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public ScoreSrcTypeEnum getSrcType() {
        return srcType;
    }

    public void setSrcType(ScoreSrcTypeEnum srcType) {
        this.srcType = srcType;
    }

    public Integer getSrcQuestionId() {
        return srcQuestionId;
    }

    public void setSrcQuestionId(Integer srcQuestionId) {
        this.srcQuestionId = srcQuestionId;
    }

    public ScoreRecordPO() {
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
