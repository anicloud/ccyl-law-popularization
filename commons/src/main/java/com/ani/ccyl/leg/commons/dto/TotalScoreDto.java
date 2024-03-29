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
    private Integer inviteCount;
    private Integer shareCount;
    private Boolean isSignIn;
    private String portrait;
    private String nickName;
    private Integer questionTime;
    public TotalScoreDto(Integer score, Integer questionCount, Integer thumbUpCount, Integer signInCount, Integer inviteCount, Integer shareCount, Boolean isSignIn, String portrait, String nickName, Integer questionTime) {
        this.score = score;
        this.questionCount = questionCount;
        this.thumbUpCount = thumbUpCount;
        this.signInCount = signInCount;
        this.inviteCount = inviteCount;
        this.shareCount = shareCount;
        this.isSignIn = isSignIn;
        this.portrait = portrait;
        this.nickName = nickName;
        this.questionTime = questionTime;
    }

    public Integer getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Integer questionTime) {
        this.questionTime = questionTime;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public Boolean getIsSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(Boolean signIn) {
        isSignIn = signIn;
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
