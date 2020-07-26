package com.espark.adarsh.service;

import com.espark.adarsh.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    public boolean save(String cacheName, Person person) {
        Cache ch = this.cacheManager.getCache(cacheName);
        if (ch != null) {
            ch.put(person.getId(), person);
            return true;
        }
        return false;
    }

    public boolean update(String cacheName, Person person) {
        Cache ch = this.cacheManager.getCache(cacheName);
        if (ch != null) {
            ch.putIfAbsent(person.getId(), person);
            return true;
        }
        return false;
    }

    public Object get(String cacheName, Long personId) {
        Cache ch = this.cacheManager.getCache(cacheName);
        if (ch != null) {
            return ch.get(personId).get();
        }
        return null;
    }

    public boolean delete(String cacheName, Long personId) {
        Cache ch = this.cacheManager.getCache(cacheName);
        if (ch != null) {
            return ch.evictIfPresent(personId);
        }
        return false;
    }

    public boolean clearCache(String cacheName) {
        Cache ch = this.cacheManager.getCache(cacheName);
        if (ch != null) {
            ch.clear();
            return true;
        }
        return false;
    }
}
