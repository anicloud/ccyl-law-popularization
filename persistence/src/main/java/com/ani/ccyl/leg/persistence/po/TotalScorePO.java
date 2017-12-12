package com.ani.ccyl.leg.persistence.po;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "t_total_score")
public class TotalScorePO implements Serializable{
    private static final long serialVersionUID = -7902800833020882787L;
    private Integer id;
    private Integer accountId;
    private Integer score;
    private Timestamp createTime;

    public TotalScorePO(Integer id, Integer accountId, Integer score, Timestamp createTime) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.createTime = createTime;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
