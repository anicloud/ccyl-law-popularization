package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

public class ShareDto implements Serializable {
    private static final long serialVersionUID = -7793410851090428758L;
    private Integer correctCount;
    private Integer totalScore;
    private String url;
    private String portrait;

    public ShareDto(Integer correctCount, Integer totalScore,String url, String portrait) {
        this.correctCount = correctCount;
        this.totalScore = totalScore;
        this.url = url;
        this.portrait = portrait;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShareDto() {
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
