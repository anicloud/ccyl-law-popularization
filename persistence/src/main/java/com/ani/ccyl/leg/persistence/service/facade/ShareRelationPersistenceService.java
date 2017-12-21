package com.ani.ccyl.leg.persistence.service.facade;

import com.ani.ccyl.leg.persistence.po.ShareRelationPO;

import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
public interface ShareRelationPersistenceService {
    ShareRelationPO findBySharedId(Integer sharedId);
}
