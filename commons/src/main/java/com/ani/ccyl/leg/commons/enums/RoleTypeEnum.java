package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 2017/12/1.
 */
public enum RoleTypeEnum implements BaseEnum<RoleTypeEnum,Integer> {
    ADMIN(1,"admin"),
    EXPERT(2,"expert"),
    WORKER(3,"worker"),
    USER(4,"user");
    private Integer code;
    private String value;
    static Map<Integer,RoleTypeEnum> enumMap= new HashMap<>();
    static {
        for(RoleTypeEnum roleTypeEnum:RoleTypeEnum.values()) {
            enumMap.put(roleTypeEnum.getCode(),roleTypeEnum);
        }
    }
    private RoleTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
    public static RoleTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
