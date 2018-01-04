package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

public class ThumbUpDto implements Serializable {
    private static final long serialVersionUID = -6679689300521976148L;
    private String toPortrait;
    private String toNickName;
    private Integer totalScore;
    private Boolean isThumbUp;

    public ThumbUpDto(String toPortrait, String toNickName, Integer totalScore, Boolean isThumbUp) {
        this.toPortrait = toPortrait;
        this.toNickName = toNickName;
        this.totalScore = totalScore;
        this.isThumbUp = isThumbUp;
    }

    public ThumbUpDto() {
    }

    public String getToPortrait() {
        return toPortrait;
    }

    public void setToPortrait(String toPortrait) {
        this.toPortrait = toPortrait;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Boolean getIsThumbUp() {
        return isThumbUp;
    }

    public void setIsThumbUp(Boolean thumbUp) {
        isThumbUp = thumbUp;
    }
}
