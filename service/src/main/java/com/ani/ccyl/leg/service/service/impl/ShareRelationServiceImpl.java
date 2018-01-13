package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.InvitedDto;
import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.mapper.ShareRelationMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;
import com.ani.ccyl.leg.service.service.facade.ShareRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
@Service
public class ShareRelationServiceImpl implements ShareRelationService {
    @Autowired
    private ShareRelationMapper shareRelationMapper;
    @Autowired
    private AccountMapper accountMapper;
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

    @Override
    public ShareRelationPO selectBySharedId(Integer sharedId) {
        ShareRelationPO relationPO = new ShareRelationPO();
        relationPO.setSharedId(sharedId);
        List<ShareRelationPO> shareRelationPOs = shareRelationMapper.select(relationPO);
        if (shareRelationPOs!=null && shareRelationPOs.size()!=0){
            return shareRelationPOs.get(0);
        }
        return null;

    }

    @Override
    public List<InvitedDto> selectByShareId(Integer shareId) {
        ShareRelationPO relationPO = new ShareRelationPO();
        List<InvitedDto> invitedDtos=new ArrayList<>();

        relationPO.setSharedId(shareId);
        relationPO.setPartIn(true);
        List<ShareRelationPO> shareRelationPOS=shareRelationMapper.selectByShareId(shareId);
        for (ShareRelationPO relationPO1:shareRelationPOS){
            AccountPO accountPO=accountMapper.selectByPrimaryKey(relationPO1.getSharedId());
            InvitedDto invitedDto =new InvitedDto(
                    accountPO.getId(),
                    accountPO.getNickName(),
                    accountPO.getPortrait(),
                    relationPO.getUpdateTime()
            );
            invitedDtos.add(invitedDto);


        }
        return invitedDtos;
    }
}
