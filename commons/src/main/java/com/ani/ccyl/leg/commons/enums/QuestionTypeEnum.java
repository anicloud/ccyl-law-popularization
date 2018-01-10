package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum QuestionTypeEnum implements BaseEnum<QuestionTypeEnum, Integer> {
    CHOICE(1,"选择"),
    JUDGEMENT(2,"判断"),
     NINETEENCHOICE(3,"19大选择"),
    NINETEENJUDGEMENT(4,"19大判断");
    private Integer code;
    private String value;
    private static Map<Integer,QuestionTypeEnum> enumMap = new HashMap<>();
    static {
        for(QuestionTypeEnum provinceEnum: QuestionTypeEnum.values()) {
            enumMap.put(provinceEnum.getCode(), provinceEnum);
        }
    }
    private QuestionTypeEnum(Integer code, String value) {
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

    public static QuestionTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
