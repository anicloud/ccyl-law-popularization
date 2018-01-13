package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;

import java.io.Serializable;

public class TotalAwardsDto implements Serializable {
    private static final long serialVersionUID = -7968726402468526796L;
    private String prodId;
    private String codeSecret;
    private AwardTypeEnum type;

    public TotalAwardsDto(String prodId, String codeSecret, AwardTypeEnum type) {
        this.prodId = prodId;
        this.codeSecret = codeSecret;
        this.type = type;
    }

    public TotalAwardsDto() {
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public AwardTypeEnum getType() {
        return type;
    }

    public void setType(AwardTypeEnum type) {
        this.type = type;
    }
}
