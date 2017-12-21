package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-20.
 */
public class Top20Dto implements Serializable {
    private static final long serialVersionUID = 1241076904330243538L;
    private Integer id;
    private String name;
    private String portrat;
    private Integer score;
    private Timestamp updateTime;

    public Top20Dto() {
    }

    public Top20Dto(Integer id, String name, String portrat, Integer score, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.portrat = portrat;
        this.score = score;
        this.updateTime = updateTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrat() {
        return portrat;
    }

    public void setPortrat(String portrat) {
        this.portrat = portrat;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
