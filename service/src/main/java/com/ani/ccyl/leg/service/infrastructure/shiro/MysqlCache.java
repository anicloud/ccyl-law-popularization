package com.ani.ccyl.leg.service.infrastructure.shiro;

import com.ani.ccyl.leg.commons.utils.SerializeUtils;
import com.ani.ccyl.leg.service.service.facade.ShiroSessionService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.*;

/**
 * Created by zhanglina on 18-1-26.
 */
public class MysqlCache<K,V> implements Cache<K,V> {
    private ShiroSessionService shiroSessionService;

    public MysqlCache(ShiroSessionService shiroSessionService) {
        this.shiroSessionService = shiroSessionService;
    }

    @Override
    public V get(K k) throws CacheException {
        byte[] values=shiroSessionService.findByShiroKey((String)k);
        if (values!=null){
            return (V)SerializeUtils.deserialize(values);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k!=null && v!=null){
            shiroSessionService.insertShiroValue((String) k,SerializeUtils.serialize(v));
        }

        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] values =null;
        if (k!=null){
             values=shiroSessionService.findByShiroKey((String)k);
            shiroSessionService.deleteByShiroKey((String) k);
        }
        if (values!=null){
            return (V)SerializeUtils.deserialize(values);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        shiroSessionService.clearAllShiros();
    }

    @Override
    public int size()

    {
        return shiroSessionService.findCount();
    }

    @Override
    public Set<K> keys() {
        Set<K> keySet=new HashSet<>();
        Set<String> keys= shiroSessionService.findAllShiroKeys();
        if (keys==null || keys.size()==0){
            return keySet;
        }
        for (String key:keys){
            K keyObj=(K) key;
            keySet.add(keyObj);
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> objectValues=new ArrayList<>();
        List<byte[]> values=shiroSessionService.findAllShiroValues();
        if (values!=null && values.size()!=0){
            for (byte[] value:values){
                objectValues.add((V)SerializeUtils.deserialize(value));
            }
        }
        return objectValues;

    }
}
