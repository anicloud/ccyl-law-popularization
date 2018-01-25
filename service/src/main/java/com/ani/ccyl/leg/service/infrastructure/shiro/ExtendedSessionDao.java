package com.ani.ccyl.leg.service.infrastructure.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExtendedSessionDao extends AbstractSessionDAO {
    private static final String KEY_PREFIX = "shiro_redis_session:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        // TODO: 2018/1/25 保存session 至redis
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        Session session = null;
        if(serializable != null) {
            // TODO: 2018/1/25 从redis根据KEY_PREFIX+sessionId读取session
//            session = (Session)redisManager.get(KEY_PREFIX + sessionId);
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        // TODO: 2018/1/25 保存session至redis
    }

    @Override
    public void delete(Session session) {
        if(session != null && session.getId()!=null) {
            // TODO: 2018/1/25 从redis删除session
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        // TODO: 2018/1/25 从redis拿出所有key 以 shiro_redis_session:开头的值并反序列化，返回
//        Set<byte[]> keys = redisManager.keys(KEY_PREFIX + "*");
        return sessions;
    }
}
