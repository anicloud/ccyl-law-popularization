package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum AwardTypeEnum implements BaseEnum<AwardTypeEnum, Integer> {
    DEFAULT(1,"默认");
    private Integer code;
    private String value;
    private static Map<Integer,AwardTypeEnum> enumMap = new HashMap<>();
    static {
        for(AwardTypeEnum provinceEnum: AwardTypeEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private AwardTypeEnum(Integer code, String value) {
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

    public static AwardTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }

    public Integer findScore() {
        Integer score = 0;
        switch (code) {
            case 1:
                score = 1000;
                break;
            case 2:
                score = 500;
                break;
        }
        return score;
    }
}
