package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.ProvinceEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "t_account")
public class AccountPO implements Serializable {
    private static final long serialVersionUID = -6283966223734937579L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String openId;
    private String accountName;
    private String accountPwd;
    private String phone;
    private String email;
    private String address;
    private ProvinceEnum province;
    private String nickName;
    private String portrait;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;
    private Boolean sex;
    private String orgName;
    private Integer age;
    private String name;

    public AccountPO(Integer id, String openId, String accountName, String accountPwd, String phone, String email, String address, ProvinceEnum province, String nickName, String portrait, Timestamp updateTime, Timestamp createTime, Boolean isDel, Boolean sex, String orgName, Integer age, String name) {
        this.id = id;
        this.openId = openId;
        this.accountName = accountName;
        this.accountPwd = accountPwd;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.province = province;
        this.nickName = nickName;
        this.portrait = portrait;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
        this.sex = sex;
        this.orgName = orgName;
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AccountPO() {
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProvinceEnum getProvince() {
        return province;
    }

    public void setProvince(ProvinceEnum province) {
        this.province = province;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
