package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum ProvinceEnum implements BaseEnum<ProvinceEnum, Integer> {
    BEIJING(1,"北京");
    private Integer code;
    private String value;
    private static Map<Integer,ProvinceEnum> enumMap = new HashMap<>();
    static {
        for(ProvinceEnum provinceEnum:ProvinceEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private ProvinceEnum(Integer code, String value) {
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

    public static ProvinceEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
