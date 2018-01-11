package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

public class CsvDto implements Serializable {
    private static final long serialVersionUID = -3363797848076447105L;
    private String nickName;
    private String province;
    private String name;
    private String phone;
    private String email;
    private String sex;
    private String orgName;
    private String age;
    private String provinceOrder;

    public CsvDto(String nickName, String province, String name, String phone, String email, String sex, String orgName, String age, String provinceOrder) {
        this.nickName = nickName;
        this.province = province;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.orgName = orgName;
        this.age = age;
        this.provinceOrder = provinceOrder;
    }

    public CsvDto() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProvinceOrder() {
        return provinceOrder;
    }

    public void setProvinceOrder(String provinceOrder) {
        this.provinceOrder = provinceOrder;
    }
}
