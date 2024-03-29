package com.ani.ccyl.leg.commons.enums;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by lihui on 2017/12/3.
 */
public enum ResponseStateEnum {
    OK(0),
    EXCEPTION(1),//exception类错误
    ERROR(2);//其他逻辑错误
    private Integer code;
    private ResponseStateEnum(Integer code) {
        this.code = code;
    }
    @JsonValue
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
