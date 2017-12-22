package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.persistence.mapper.ShareRelationMapper;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;
import com.ani.ccyl.leg.service.service.facade.ShareRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
@Service
public class ShareRelationServiceImpl implements ShareRelationService {
    @Autowired
    private ShareRelationMapper shareRelationMapper;
    @Override
    public void insert(Integer shareId, Integer sharedId, Boolean isPartIn) {
        ShareRelationPO relationPO = new ShareRelationPO();
        relationPO.setSharedId(sharedId);
        List<ShareRelationPO> shareRelationPOs = shareRelationMapper.select(relationPO);
        if(shareRelationPOs.size()==0) {
            relationPO.setShareId(shareId);
            relationPO.setPartIn(isPartIn);
            shareRelationMapper.insertSelective(relationPO);
        }
    }
}
