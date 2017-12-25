package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-25.
 */
public class AwardDto implements Serializable {
    private static final long serialVersionUID = 2112431259790987270L;
    private Integer lastScore;
    private AwardTypeEnum awardType;

    public AwardDto(Integer lastScore, AwardTypeEnum awardType) {
        this.lastScore = lastScore;
        this.awardType = awardType;
    }

    public AwardDto() {
    }

    public Integer getLastScore() {
        return lastScore;
    }

    public void setLastScore(Integer lastScore) {
        this.lastScore = lastScore;
    }

    public AwardTypeEnum getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardTypeEnum awardType) {
        this.awardType = awardType;
    }
}
