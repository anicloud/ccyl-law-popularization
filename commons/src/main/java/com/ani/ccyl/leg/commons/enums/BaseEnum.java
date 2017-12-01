package com.ani.ccyl.leg.commons.enums;

/**
 * Created by lihui on 2017/12/1.
 */
public interface BaseEnum<E extends Enum<?>, T> {
    public T getCode();
    public String getValue();
}
