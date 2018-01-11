package com.ani.ccyl.leg.commons.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhanglina on 18-1-10.
 */
public class InvitedDto {
    Integer accountId;
    String nickName;
    String portrait;
    Timestamp updateTime;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public InvitedDto(Integer accountId, String nickName, String portrait, Timestamp updateTime) {

        this.accountId = accountId;
        this.nickName = nickName;
        this.portrait = portrait;
        this.updateTime = updateTime;
    }
}
