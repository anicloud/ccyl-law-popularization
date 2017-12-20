package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum ProvinceEnum implements BaseEnum<ProvinceEnum, Integer> {
    BEI_JING(1,"北京"),
    AN_HUI(2,"安徽"),
    FU_JIAN(3,"福建"),
    GAN_SU(4,"甘肃"),
    GUANG_DONG(5,"广东"),
    GUANG_XI(6,"广西"),
    GUI_ZHOU(7,"贵州"),
    HAI_NAN(8,"海南"),
    HE_BEI(9,"河北"),
    HE_NAN(10,"河南"),
    HEI_LONG_JIANG(11,"黑龙江"),
    HU_BEI(12,"湖北"),
    HU_NAN(13,"湖南"),
    JI_LIN(14,"吉林"),
    JIANG_SU(15,"江苏"),
    JIANG_XI(16,"江西"),
    LIAO_NING(17,"辽宁"),
    INNER_MONGORIA(18,"内蒙古"),
    NING_XIA(19,"宁夏"),
    QING_HAI(20,"青海"),
    SHAN_DONG(21,"山东"),
    SHAN_XI(22,"山西"),
    SHAAN_XI(23,"陕西"),
    SHANG_HAI(24,"上海"),
    SI_CHUAN(25,"四川"),
    TIAN_JIN(26,"天津"),
    TIBET(27,"西藏"),
    XIN_JIANG(28,"新疆"),
    YUN_NAN(29,"云南"),
    ZHE_JIANG(30,"浙江"),
    CHONG_QING(31,"重庆"),
    MACAO(32,"澳门"),
    HONG_KONG(33,"香港"),
    TAI_WAN(34,"台湾");
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
