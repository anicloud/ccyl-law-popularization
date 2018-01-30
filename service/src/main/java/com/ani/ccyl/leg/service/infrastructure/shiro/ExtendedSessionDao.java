package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.commons.utils.SerializeUtils;
import com.ani.ccyl.leg.service.service.facade.ShiroSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.*;

public class ExtendedSessionDao extends AbstractSessionDAO {
    private static final String KEY_PREFIX = "shiro_redis_session:";
    private ShiroSessionService shiroSessionService;

    public ShiroSessionService getShiroSessionService() {
        return shiroSessionService;
    }

    public void setShiroSessionService(ShiroSessionService shiroSessionService) {
        this.shiroSessionService = shiroSessionService;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session,sessionId);
        shiroSessionService.insertSession(sessionId.toString(),SerializeUtils.serialize(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        Session session = null;
        if(serializable != null) {
           byte[] sessionBytes= shiroSessionService.findbySessionId(serializable.toString());
            if (sessionBytes!=null){
                session=(Session) SerializeUtils.deserialize(sessionBytes);
            }
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session != null && session.getId()!=null) {
            shiroSessionService.updateSession(session.getId().toString(),SerializeUtils.serialize(session));
        }

    }

    @Override
    public void delete(Session session) {
        if(session != null && session.getId()!=null) {
            shiroSessionService.deleteBySessionId(session.getId().toString());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        List<byte[]> sessionValues=shiroSessionService.findAllSessionValues();
        if (sessionValues!=null && sessionValues.size()!=0){
            for (byte[] bytes:sessionValues){
                sessions.add((Session)SerializeUtils.deserialize(bytes));
            }
        }
        return sessions;
    }
}
