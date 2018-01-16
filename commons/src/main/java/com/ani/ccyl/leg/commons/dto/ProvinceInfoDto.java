package com.ani.ccyl.leg.commons.dto;

/**
 * Created by zhanglina on 18-1-16.
 */
public class ProvinceInfoDto {
    private String province;
    private Integer averageScore;
    private Integer totalScore;
    private Integer peopleNumber;

    public ProvinceInfoDto(String province, Integer averageScore, Integer totalScore, Integer peopleNumber) {
        this.province = province;
        this.averageScore = averageScore;
        this.totalScore = totalScore;
        this.peopleNumber = peopleNumber;
    }

    public ProvinceInfoDto() {
    }

    public String getProvince() {

        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }


}
