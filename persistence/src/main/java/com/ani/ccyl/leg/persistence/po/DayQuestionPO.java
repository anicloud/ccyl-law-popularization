package com.ani.ccyl.leg.persistence.po;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-18.
 */
@Table(name = "t_day_question")
public class DayQuestionPO implements Serializable {
    private static final long serialVersionUID = 9178619804645371178L;
    @Id
    private Integer id;
    private Integer dayNum;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;
    private Integer order;

    public DayQuestionPO() {
    }

    public DayQuestionPO(Integer id, Integer dayNum, Timestamp updateTime, Timestamp createTime, Boolean isDel, Integer order) {
        this.id = id;
        this.dayNum = dayNum;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
        this.order = order;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
