package com.ani.ccyl.leg.persistence.po;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "v_day_score")
public class DayScorePO implements Serializable {
    private static final long serialVersionUID = -3229125841911050593L;
    private Integer id;
    private Integer accountId;
    private Integer score;

    public DayScorePO(Integer id, Integer accountId, Integer score) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
    }

    public DayScorePO() {
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
}
