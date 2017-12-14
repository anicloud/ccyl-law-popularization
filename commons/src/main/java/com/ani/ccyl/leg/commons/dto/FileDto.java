package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.BusinessTypeEnum;
import com.ani.ccyl.leg.commons.enums.FileTypeEnum;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-14.
 */
public class FileDto implements Serializable {
    private static final long serialVersionUID = -1772649760579427711L;
    private Integer id;
    private String path;
    private FileTypeEnum type;
    private BusinessTypeEnum busiType;
    private String name;

    public FileDto() {
    }

    public FileDto(Integer id, String path, FileTypeEnum type, BusinessTypeEnum busiType, String name) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.busiType = busiType;
        this.name = name;
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
}
