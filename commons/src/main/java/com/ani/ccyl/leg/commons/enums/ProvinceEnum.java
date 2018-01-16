package com.ani.ccyl.leg.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihui on 17-12-12.
 */
public enum ProvinceEnum implements BaseEnum<ProvinceEnum, Integer> {
    BEI_JING(1,"北京市"),
    AN_HUI(2,"安徽省"),
    FU_JIAN(3,"福建省"),
    GAN_SU(4,"甘肃省"),
    GUANG_DONG(5,"广东省"),
    GUANG_XI(6,"广西省"),
    GUI_ZHOU(7,"贵州省"),
    HAI_NAN(8,"海南省"),
    HE_BEI(9,"河北省"),
    HE_NAN(10,"河南省"),
    HEI_LONG_JIANG(11,"黑龙江省"),
    HU_BEI(12,"湖北省"),
    HU_NAN(13,"湖南省"),
    JI_LIN(14,"吉林省"),
    JIANG_SU(15,"江苏省"),
    JIANG_XI(16,"江西省"),
    LIAO_NING(17,"辽宁省"),
    INNER_MONGORIA(18,"内蒙古"),
    NING_XIA(19,"宁夏"),
    QING_HAI(20,"青海"),
    SHAN_DONG(21,"山东省"),
    SHAN_XI(22,"山西省"),
    SHAAN_XI(23,"陕西省"),
    SHANG_HAI(24,"上海市"),
    SI_CHUAN(25,"四川省"),
    TIAN_JIN(26,"天津市"),
    TIBET(27,"西藏"),
    XIN_JIANG(28,"新疆"),
    YUN_NAN(29,"云南省"),
    ZHE_JIANG(30,"浙江省"),
    CHONG_QING(31,"重庆市"),
    MACAO(32,"澳门"),
    HONG_KONG(33,"香港"),
    TAI_WAN(34,"台湾"),
    NUll(35,"NA");
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

    public static ProvinceEnum getEnum(String value) {
        for(ProvinceEnum provinceEnum:ProvinceEnum.values()) {
            if(value.contains(provinceEnum.getValue())) {
                return provinceEnum;
            }
        }
        return null;
    }
}
