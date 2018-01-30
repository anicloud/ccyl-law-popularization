package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.ShiroSessionPO;

import java.util.List;

/**
 * Created by zhanglina on 18-1-26.
 */
public interface ShiroSessionMapper extends SysMapper<ShiroSessionPO> {
    public void clearAllShiros();
    public List<ShiroSessionPO> findAllShiros();
    public Integer findCount();
    public List<ShiroSessionPO> findAllSessions();
    public void clearExpiredSession(Long createTime);
}
