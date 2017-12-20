package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-20.
 */
public class DailyTotalScoreDto implements Serializable {
    private static final long serialVersionUID = -6535032708267249745L;
    private Integer accountId;
    private Integer score;
    private String name;
    private String portrait;

    public DailyTotalScoreDto() {
    }

    public DailyTotalScoreDto(Integer accountId, Integer score, String name, String portrait) {
        this.accountId = accountId;
        this.score = score;
        this.name = name;
        this.portrait = portrait;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
