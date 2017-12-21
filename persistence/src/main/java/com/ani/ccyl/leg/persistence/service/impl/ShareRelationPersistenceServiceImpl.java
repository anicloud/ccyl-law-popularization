package com.ani.ccyl.leg.persistence.service.impl;

import com.ani.ccyl.leg.persistence.mapper.ShareRelationMapper;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;
import com.ani.ccyl.leg.persistence.service.facade.ShareRelationPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
@Service
public class ShareRelationPersistenceServiceImpl implements ShareRelationPersistenceService {
    @Autowired
    private ShareRelationMapper shareRelationMapper;
    @Override
    public ShareRelationPO findBySharedId(Integer sharedId) {
        ShareRelationPO relationPO = new ShareRelationPO();
        relationPO.setSharedId(sharedId);
        List<ShareRelationPO> shareRelationPOs = shareRelationMapper.select(relationPO);
        if(shareRelationPOs.size()>0){
            return shareRelationPOs.get(0);
        }
        return null;
    }
}
