package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-22.
 */
public class TotalSignInDto implements Serializable {
    private static final long serialVersionUID = 6611997774907924597L;
    private List<Timestamp> createTime;
    private Boolean isSignIn;

    public TotalSignInDto() {
    }

    public TotalSignInDto(List<Timestamp> createTime, Boolean isSignIn) {
        this.createTime = createTime;
        this.isSignIn = isSignIn;
    }

    public List<Timestamp> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<Timestamp> createTime) {
        this.createTime = createTime;
    }

    public Boolean getSignIn() {
        return isSignIn;
    }

    public void setSignIn(Boolean signIn) {
        isSignIn = signIn;
    }
}
