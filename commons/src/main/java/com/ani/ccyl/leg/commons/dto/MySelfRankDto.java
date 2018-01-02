package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MySelfRankDto implements Serializable {
    private static final long serialVersionUID = -1020419472875465165L;
    private String portrait;
    private String nickName;
    private Integer totalScore;
    private Integer ranking;

    public MySelfRankDto(String portrait, String nickName, Integer totalScore, Integer ranking) {
        this.portrait = portrait;
        this.nickName = nickName;
        this.totalScore = totalScore;
        this.ranking = ranking;
    }

    public MySelfRankDto() {
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
        try {
            this.nickName = URLDecoder.decode(nickName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("nickName 解码失败");
        }
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
