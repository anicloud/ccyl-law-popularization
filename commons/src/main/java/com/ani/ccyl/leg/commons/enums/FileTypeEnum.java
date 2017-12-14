package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-14.
 */
public enum FileTypeEnum implements BaseEnum<FileTypeEnum,Integer>{
    EXCEL(1,"EXCEL");

    private Integer code;
    private String value;

    private static Map<Integer,FileTypeEnum> enumMap = new HashMap<>();
    static {
        for(FileTypeEnum fileTypeEnum:FileTypeEnum.values()) {
            enumMap.put(fileTypeEnum.getCode(),fileTypeEnum);
        }
    }
    private FileTypeEnum(Integer code, String value) {
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
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }

    public static FileTypeEnum getEnum(Integer code) {
        return enumMap.get(code);
    }
}
