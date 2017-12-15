package com.ani.ccyl.leg.commons.exception;

import com.ani.ccyl.leg.commons.enums.ExceptionEnum;

/**
 * Created by lihui on 17-12-15.
 */
public class SMSException extends RuntimeException {
    private static final long serialVersionUID = 8042123847858292449L;
    private ExceptionEnum type;

    public SMSException(String message, ExceptionEnum type) {
        super(message);
        this.type = type;
    }

    public ExceptionEnum getType() {
        return type;
    }

    public void setType(ExceptionEnum type) {
        this.type = type;
    }
}
