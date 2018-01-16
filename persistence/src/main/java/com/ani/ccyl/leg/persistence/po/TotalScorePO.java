package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_total_score")
public class TotalScorePO implements Serializable {
    private static final long serialVersionUID = -7902800833020882787L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer accountId;
    private Integer score;
    private ProvinceEnum province;
    private Integer thumbUpCount;
    private Integer signInCount;
    private Integer inviteCount;
    private Integer shareCount;

    public TotalScorePO(Integer id, Integer accountId, Integer score, ProvinceEnum province) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.province = province;
    }

    public TotalScorePO(Integer accountId, Integer score, ProvinceEnum province, Integer thumbUpCount, Integer signInCount, Integer inviteCount, Integer shareCount) {
        this.accountId = accountId;
        this.score = score;
        this.province = province;
        this.thumbUpCount = thumbUpCount;
        this.signInCount = signInCount;
        this.inviteCount = inviteCount;
        this.shareCount = shareCount;
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

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public TotalScorePO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ProvinceEnum getProvince() {
        return province;
    }

    public void setProvince(ProvinceEnum province) {
        this.province = province;
    }
}
