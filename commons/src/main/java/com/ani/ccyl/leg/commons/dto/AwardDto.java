package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-28.
 */
public class AwardDto implements Serializable {
    private static final long serialVersionUID = 2112431259790987270L;
    private AwardTypeEnum awardType;
    private Boolean isUsedUp;
    private Integer score;
    private Integer myScore;

    public AwardDto() {
    }

    public AwardDto(AwardTypeEnum awardType, Boolean isUsedUp, Integer score, Integer myScore) {
        this.awardType = awardType;
        this.isUsedUp = isUsedUp;
        this.score = score;
        this.myScore = myScore;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public AwardTypeEnum getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardTypeEnum awardType) {
        this.awardType = awardType;
    }

    public Boolean getUsedUp() {
        return isUsedUp;
    }

    public void setUsedUp(Boolean usedUp) {
        isUsedUp = usedUp;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
