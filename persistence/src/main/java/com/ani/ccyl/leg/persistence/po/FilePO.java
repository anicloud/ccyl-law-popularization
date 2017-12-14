package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.BusinessTypeEnum;
import com.ani.ccyl.leg.commons.enums.FileTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-14.
 */
@Table(name = "t_file")
public class FilePO implements Serializable {
    private static final long serialVersionUID = 8819535985396667040L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String path;
    private FileTypeEnum type;
    private BusinessTypeEnum busiType;
    private String name;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;

    public FilePO() {
    }

    public FilePO(Integer id, String path, FileTypeEnum type, BusinessTypeEnum busiType, String name, Timestamp updateTime, Timestamp createTime, Boolean isDel) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.busiType = busiType;
        this.name = name;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileTypeEnum getType() {
        return type;
    }

    public void setType(FileTypeEnum type) {
        this.type = type;
    }

    public BusinessTypeEnum getBusiType() {
        return busiType;
    }

    public void setBusiType(BusinessTypeEnum busiType) {
        this.busiType = busiType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
