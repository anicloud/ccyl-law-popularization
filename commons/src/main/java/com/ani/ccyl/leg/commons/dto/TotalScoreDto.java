package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-19.
 */
public class TotalScoreDto implements Serializable {
    private static final long serialVersionUID = -5884367917159161040L;
    private Integer score;
    private Integer questionCount;
    private Integer thumbUpCount;
    private Integer signInCount;
    private Integer shareCount;

    public TotalScoreDto(Integer score, Integer questionCount, Integer thumbUpCount, Integer signInCount, Integer shareCount) {
        this.score = score;
        this.questionCount = questionCount;
        this.thumbUpCount = thumbUpCount;
        this.signInCount = signInCount;
        this.shareCount = shareCount;
    }

    public TotalScoreDto() {
    }
    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(Integer thumbUpCount) {
        this.thumbUpCount = thumbUpCount;
    }

    public Integer getSignInCount() {
        return signInCount;
    }

    public void setSignInCount(Integer signInCount) {
        this.signInCount = signInCount;
    }
}
