package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.ShareRelationPO;

import java.util.List;

/**
 * Created by lihui on 17-12-21.
 */
public interface ShareRelationMapper extends SysMapper<ShareRelationPO> {
    List<ShareRelationPO> selectByShareId(ShareRelationPO relationPO);

}
