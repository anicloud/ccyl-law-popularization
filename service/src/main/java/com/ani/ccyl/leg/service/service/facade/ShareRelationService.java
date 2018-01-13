package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.InvitedDto;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
public interface ShareRelationService {
    void insert(Integer shareId, Integer sharedId, Boolean isPartIn);
    ShareRelationPO selectBySharedId(Integer sharedId);
    List<InvitedDto> selectByShareId(Integer shareId) throws UnsupportedEncodingException;
}
