package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum CategoryEnum implements BaseEnum<CategoryEnum, Integer> {
    BEIJING(1,"默认");
    private Integer code;
    private String value;
    private static Map<Integer,CategoryEnum> enumMap = new HashMap<>();
    static {
        for(CategoryEnum provinceEnum: CategoryEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private CategoryEnum(Integer code, String value) {
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

    public static CategoryEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
