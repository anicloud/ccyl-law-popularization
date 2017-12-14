package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.FileDto;
import com.ani.ccyl.leg.persistence.po.FilePO;

import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-14.
 */
public class FileAdapter {
    public static FilePO fromDto(FileDto fileDto) {
        if(fileDto != null) {
            return new FilePO(
                    fileDto.getId() == null?null:fileDto.getId(),
                    fileDto.getPath(),
                    fileDto.getType(),
                    fileDto.getBusiType(),
                    fileDto.getName(),
                    null,
                    new Timestamp(System.currentTimeMillis()),
                    false
            );
        }
        return null;
    }
}
