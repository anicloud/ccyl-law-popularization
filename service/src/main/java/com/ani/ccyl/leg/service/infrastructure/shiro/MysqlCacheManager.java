package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.service.service.facade.ShiroSessionService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class MysqlCacheManager implements CacheManager {
    private ShiroSessionService shiroSessionService;

    public ShiroSessionService getShiroSessionService() {
        return shiroSessionService;
    }

    public void setShiroSessionService(ShiroSessionService shiroSessionService) {
        this.shiroSessionService = shiroSessionService;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new MysqlCache<>(shiroSessionService);
    }
}
