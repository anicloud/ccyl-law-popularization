package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum BusinessTypeEnum implements BaseEnum<BusinessTypeEnum, Integer> {
    DEFAULT(1,"默认");
    private Integer code;
    private String value;
    private static Map<Integer,BusinessTypeEnum> enumMap = new HashMap<>();
    static {
        for(BusinessTypeEnum provinceEnum: BusinessTypeEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private BusinessTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static BusinessTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
