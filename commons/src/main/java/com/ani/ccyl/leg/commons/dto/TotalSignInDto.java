package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-22.
 */
public class TotalSignInDto implements Serializable {
    private static final long serialVersionUID = 6611997774907924597L;
    private List<Integer> signInDays;
    private Boolean isSignIn;

    public TotalSignInDto() {
    }

    public TotalSignInDto(List<Integer> signInDays, Boolean isSignIn) {
        this.signInDays = signInDays;
        this.isSignIn = isSignIn;
    }

    public List<Integer> getSignInDays() {
        return signInDays;
    }

    public void setSignInDays(List<Integer> signInDays) {
        this.signInDays = signInDays;
    }

    public Boolean getSignIn() {
        return isSignIn;
    }

    public void setSignIn(Boolean signIn) {
        isSignIn = signIn;
    }
}
