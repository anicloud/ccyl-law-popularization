package com.ani.ccyl.leg.commons.exception;

import com.ani.ccyl.leg.commons.enums.ExceptionEnum;

/**
 * Created by lihui on 17-12-13.
 */
public class WechatException extends RuntimeException {
    private ExceptionEnum type;

    public ExceptionEnum getType() {
        return type;
    }

    public void setType(ExceptionEnum type) {
        this.type = type;
    }

    public WechatException(String message, ExceptionEnum type) {
        super(message);
        this.type = type;
    }
}
