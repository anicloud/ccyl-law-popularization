package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum ScoreSrcTypeEnum implements BaseEnum<ScoreSrcTypeEnum, Integer> {
    QUESTION(1,"答题"),
    THUMB_UP(2,"点赞"),
    SIGN_IN(3,"签到");
    private Integer code;
    private String value;
    private static Map<Integer,ScoreSrcTypeEnum> enumMap = new HashMap<>();
    static {
        for(ScoreSrcTypeEnum provinceEnum: ScoreSrcTypeEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private ScoreSrcTypeEnum(Integer code, String value) {
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

    public static ScoreSrcTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
