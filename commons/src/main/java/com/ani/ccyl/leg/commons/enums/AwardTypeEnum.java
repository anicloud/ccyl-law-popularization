package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum AwardTypeEnum implements BaseEnum<AwardTypeEnum, Integer> {
    TENCENT_VIP(1,"腾讯视频会员月卡"),
    OFO_COUPON(2,"ofo10元代金券"),
    FIVE_COUPON(3,"满49减5元"),
    TEN_COUPON(4,"满99减10元"),
    TOP_1(5,"第一名"),
    TOP_2(6,"第二名"),
    TOP_3(7,"第三名"),
    TOP_4S(8,"第四名以上");
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
            case 3:
                score = 200;
                break;
            case 4:
                score = 100;
                break;
        }
        return score;
    }
    public static AwardTypeEnum getTopEnum(Integer order) {
        AwardTypeEnum type = null;
        switch (order) {
            case 1:
                type = AwardTypeEnum.TOP_1;
                break;
            case 2:
                type = AwardTypeEnum.TOP_2;
                break;
            case 3:
                type = AwardTypeEnum.TOP_3;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                type = AwardTypeEnum.TOP_4S;
                break;
        }
        return type;
    }
}
