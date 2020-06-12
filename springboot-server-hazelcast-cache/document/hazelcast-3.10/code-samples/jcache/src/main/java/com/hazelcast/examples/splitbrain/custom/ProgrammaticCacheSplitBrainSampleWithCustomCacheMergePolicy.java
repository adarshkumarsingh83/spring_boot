package com.hazelcast.examples.splitbrain.custom;

import com.hazelcast.config.CacheConfig;
import com.hazelcast.config.Config;

import javax.cache.Cache;
import javax.cache.CacheManager;

/**
 * Programmatically configured version of custom cache merge policy based jcache split-brain sample.
 */
public class ProgrammaticCacheSplitBrainSampleWithCustomCacheMergePolicy
        extends AbstractCacheSplitBrainSampleWithCustomCacheMergePolicy {

    @Override
    protected Config getConfig() {
        return newProgrammaticConfig();
    }

    @Override
    protected Cache<String, Object> getCache(String cacheName, CacheManager cacheManager) {
        CacheConfig<String, Object> cacheConfig = newCacheConfig(cacheName, CustomCacheMergePolicy.class.getName());
        return cacheManager.createCache(cacheName, cacheConfig);
    }

    public static void main(String[] args) {
        new ProgrammaticCacheSplitBrainSampleWithCustomCacheMergePolicy().run();
    }
}
