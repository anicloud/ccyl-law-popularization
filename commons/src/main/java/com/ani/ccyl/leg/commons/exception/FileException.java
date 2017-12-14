package com.ani.ccyl.leg.commons.exception;

import com.ani.ccyl.leg.commons.enums.ExceptionEnum;

/**
 * Created by lihui on 17-12-14.
 */
public class FileException extends RuntimeException {

    private static final long serialVersionUID = -6108985078891351431L;

    private ExceptionEnum type;

    public ExceptionEnum getType() {
        return type;
    }

    public void setType(ExceptionEnum type) {
        this.type = type;
    }

    public FileException(String message, ExceptionEnum type) {
        super(message);
        this.type = type;
    }
}
