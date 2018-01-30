package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.ShiroSessionDto;
import com.ani.ccyl.leg.persistence.po.ShiroSessionPO;

import javax.websocket.Session;
import java.util.List;
import java.util.Set;

/**
 * Created by zhanglina on 18-1-26.
 */
public interface ShiroSessionService {
    public void insertSession(String sessionId,byte[] session);
    public void insertShiroValue(String shiroKey,byte[] shiroValue);
    public void deleteByShiroKey(String shiroKey);
    public void deleteBySessionId(String sessionId);
    public void updateSession(String sessionId, byte[] session);
    public void updateShiro(String shiroKey,byte[] shiroValue);
    public byte[] findbySessionId(String sessionId);
    public  byte[] findByShiroKey(String shiroKey);

    public List<byte[]> findAllSessionValues();

    public void clearAllShiros();
    public Set<String> findAllShiroKeys();
    public List<byte[]> findAllShiroValues();
    public Integer findCount();

    public void clearExpiredSession();
}
