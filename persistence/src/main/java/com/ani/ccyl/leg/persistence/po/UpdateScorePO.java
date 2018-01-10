package com.ani.ccyl.leg.persistence.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by sunminggui on 2018/1/10.
 */
@Table(name = "t_total_score")
public class UpdateScorePO implements Serializable {
    private static final long serialVersionUID = 4427057947374199785L;
    @Id
    private Integer accountId;
    private Integer deleteScore;

    public UpdateScorePO(Integer accountId, Integer deleteScore) {
        this.accountId = accountId;
        this.deleteScore = deleteScore;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDeleteScore() {
        return deleteScore;
    }

    public void setDeleteScore(Integer deleteScore) {
        this.deleteScore = deleteScore;
    }
}
