package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.ResponseStateEnum;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-3.
 */
public class ResponseMessageDto implements Serializable{
    private static final long serialVersionUID = -4002424118653998970L;
    private ResponseStateEnum state;
    private Object data;
    private String msg;

    public ResponseMessageDto() {
    }

    public ResponseMessageDto(ResponseStateEnum state, Object data, String msg) {
        this.state = state;
        this.data = data;
        this.msg = msg;
    }

    public ResponseStateEnum getState() {
        return state;
    }

    public void setState(ResponseStateEnum state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
