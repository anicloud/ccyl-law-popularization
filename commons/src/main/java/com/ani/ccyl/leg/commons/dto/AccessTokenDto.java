package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-13.
 */
public class AccessTokenDto implements Serializable {
    private static final long serialVersionUID = 1993403059183574263L;
    private Integer id;
    private String accessToken;
    private Long tokenExpiresIn;
    private Long ticketExpiresIn;
    private String jsapiTicket;
    private Timestamp tokenCreateTime;
    private Timestamp ticketCreateTime;

    public AccessTokenDto() {
    }

    public AccessTokenDto(Integer id, String accessToken, Long tokenExpiresIn, Long ticketExpiresIn, String jsapiTicket, Timestamp tokenCreateTime, Timestamp ticketCreateTime) {
        this.id = id;
        this.accessToken = accessToken;
        this.tokenExpiresIn = tokenExpiresIn;
        this.ticketExpiresIn = ticketExpiresIn;
        this.jsapiTicket = jsapiTicket;
        this.tokenCreateTime = tokenCreateTime;
        this.ticketCreateTime = ticketCreateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public void setTokenExpiresIn(Long tokenExpiresIn) {
        this.tokenExpiresIn = tokenExpiresIn;
    }

    public Long getTicketExpiresIn() {
        return ticketExpiresIn;
    }

    public void setTicketExpiresIn(Long ticketExpiresIn) {
        this.ticketExpiresIn = ticketExpiresIn;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public Timestamp getTokenCreateTime() {
        return tokenCreateTime;
    }

    public void setTokenCreateTime(Timestamp tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }

    public Timestamp getTicketCreateTime() {
        return ticketCreateTime;
    }

    public void setTicketCreateTime(Timestamp ticketCreateTime) {
        this.ticketCreateTime = ticketCreateTime;
    }
}
