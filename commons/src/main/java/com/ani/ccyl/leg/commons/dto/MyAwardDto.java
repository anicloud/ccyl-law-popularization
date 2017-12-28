package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-25.
 */
public class MyAwardDto implements Serializable {
    private static final long serialVersionUID = 2112431259790987270L;
    private Integer lastScore;
    private AwardTypeEnum awardType;
    private String codeSecret;
    private Boolean isExpired;
    private Boolean isReceivedAward;
    private Timestamp createTime;

    public MyAwardDto(Integer lastScore, AwardTypeEnum awardType, String codeSecret, Boolean isExpired, Boolean isReceivedAward, Timestamp createTime) {
        this.lastScore = lastScore;
        this.awardType = awardType;
        this.codeSecret = codeSecret;
        this.isExpired = isExpired;
        this.isReceivedAward = isReceivedAward;
        this.createTime = createTime;
    }

    public Boolean getIsReceivedAward() {
        return isReceivedAward;
    }

    public void setIsReceivedAward(Boolean receivedAward) {
        isReceivedAward = receivedAward;
    }

    public MyAwardDto() {
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean expired) {
        isExpired = expired;
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
