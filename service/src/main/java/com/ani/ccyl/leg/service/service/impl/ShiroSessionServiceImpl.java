package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.ShiroSessionDto;
import com.ani.ccyl.leg.persistence.mapper.ShiroSessionMapper;
import com.ani.ccyl.leg.persistence.po.ShiroSessionPO;
import com.ani.ccyl.leg.service.service.facade.ShiroSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhanglina on 18-1-26.
 */
@Service
public class ShiroSessionServiceImpl implements ShiroSessionService{

    @Autowired
    ShiroSessionMapper shiroSessionMapper;

    @Override
    public void insertSession(String sessionId, byte[] session) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setSessionId(sessionId);
        shiroSessionPO.setSessionValue(session);
        shiroSessionPO.setCreateTime(System.currentTimeMillis());
        shiroSessionMapper.insert(shiroSessionPO);
    }

    @Override
    public void insertShiroValue(String shiroKey, byte[] shiroValue) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setShiroKey(shiroKey);
        shiroSessionPO.setShiroValue(shiroValue);
        shiroSessionPO.setCreateTime(System.currentTimeMillis());
        shiroSessionMapper.insert(shiroSessionPO);
    }

    @Override
    public void deleteByShiroKey(String shiroKey) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setShiroKey(shiroKey);
        shiroSessionMapper.delete(shiroSessionPO);

    }

    @Override
    public void deleteBySessionId(String sessionId) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setSessionId(sessionId);
        shiroSessionMapper.delete(shiroSessionPO);
    }

    @Override
    public void updateSession(String sessionId, byte[] session) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setSessionId(sessionId);
       List<ShiroSessionPO>  orgPOs=shiroSessionMapper.select(shiroSessionPO);
        if (orgPOs!=null && orgPOs.size()!=0){
            ShiroSessionPO orgPO=orgPOs.get(0);
            orgPO.setSessionValue(session);
            orgPO.setCreateTime(System.currentTimeMillis());
            shiroSessionMapper.updateByPrimaryKeySelective(orgPO);
        }

    }

    @Override
    public void updateShiro(String shiroKey, byte[] shiroValue) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setShiroKey(shiroKey);
        List<ShiroSessionPO>  orgPOs=shiroSessionMapper.select(shiroSessionPO);
        if (orgPOs!=null && orgPOs.size()!=0){
            ShiroSessionPO orgPO=orgPOs.get(0);
            orgPO.setShiroValue(shiroValue);
            shiroSessionMapper.updateByPrimaryKeySelective(orgPO);
        }
    }

    @Override
    public byte[] findbySessionId(String sessionId) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setSessionId(sessionId);
        List<ShiroSessionPO>  orgPOs=shiroSessionMapper.select(shiroSessionPO);
        if (orgPOs!=null && orgPOs.size()!=0){
            ShiroSessionPO orgPO=orgPOs.get(0);
           return orgPO.getSessionValue();
        }
        return null;

    }

    @Override
    public byte[] findByShiroKey(String shiroKey) {
        ShiroSessionPO shiroSessionPO=new ShiroSessionPO();
        shiroSessionPO.setShiroKey(shiroKey);
        List<ShiroSessionPO>  orgPOs=shiroSessionMapper.select(shiroSessionPO);
        if (orgPOs!=null && orgPOs.size()!=0){
            ShiroSessionPO orgPO=orgPOs.get(0);
            return orgPO.getShiroValue();
        }
        return null;
    }

    @Override
    public List<byte[]> findAllSessionValues() {
        List<byte[]> sessionValues = new ArrayList<>();
        List<ShiroSessionPO> shiroSessionPOS=shiroSessionMapper.findAllSessions();
        if (shiroSessionPOS!=null && shiroSessionPOS.size()!=0){
            for (ShiroSessionPO shiroSessionPO:shiroSessionPOS){
                sessionValues.add(shiroSessionPO.getSessionValue());
            }

        }
        return sessionValues;
    }

    @Override
    public void clearAllShiros() {
        shiroSessionMapper.clearAllShiros();
    }

    @Override
    public Set<String> findAllShiroKeys() {
        Set<String> keys=new HashSet<>();
        List<ShiroSessionPO> shiroSessionPOS=shiroSessionMapper.findAllShiros();
        if (shiroSessionPOS!=null && shiroSessionPOS.size()!=0){
            for (ShiroSessionPO shiroSessionPO:shiroSessionPOS){
                keys.add(shiroSessionPO.getShiroKey());
            }
        }
        return keys;

    }

    @Override
    public List<byte[]> findAllShiroValues() {
        List<byte[]> values=new ArrayList<>();
        List<ShiroSessionPO> shiroSessionPOS=shiroSessionMapper.findAllShiros();
        if (shiroSessionPOS!=null && shiroSessionPOS.size()!=0){
            for (ShiroSessionPO shiroSessionPO:shiroSessionPOS){
                values.add(shiroSessionPO.getShiroValue());
            }
        }
        return values;
    }

    @Override
    public Integer findCount() {

        return shiroSessionMapper.findCount();
    }

    @Override
    public void clearExpiredSession() {
        Long createTime=System.currentTimeMillis()-30*60*1000;
        shiroSessionMapper.clearExpiredSession(createTime);


    }
}
