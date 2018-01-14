package com.ani.ccyl.leg.persistence.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-21.
 */
@Table(name = "t_share_relation")
public class ShareRelationPO implements Serializable {
    private static final long serialVersionUID = -3438378572809818947L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private Integer shareId;
    private Integer sharedId;
    private Boolean isPartIn;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public ShareRelationPO() {
    }

    public ShareRelationPO(Integer id, Integer shareId, Integer sharedId, Boolean isPartIn, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.shareId = shareId;
        this.sharedId = sharedId;
        this.isPartIn = isPartIn;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getSharedId() {
        return sharedId;
    }

    public void setSharedId(Integer sharedId) {
        this.sharedId = sharedId;
    }

    public Boolean getIsPartIn() {
        return isPartIn;
    }

    public void setIsPartIn(Boolean partIn) {
        isPartIn = partIn;
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
