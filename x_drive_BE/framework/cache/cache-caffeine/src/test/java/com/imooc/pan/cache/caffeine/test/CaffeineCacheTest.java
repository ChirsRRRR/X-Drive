package com.imooc.pan.cache.caffeine.test;

import cn.hutool.core.lang.Assert;
import com.imooc.pan.cache.caffeine.test.config.CaffeineCacheConfigTest;
import com.imooc.pan.cache.caffeine.test.instance.CacheAnnotationTester;
import com.imooc.pan.cache.core.constants.CacheConstants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Caffeine缓存测试单元
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CaffeineCacheConfigTest.class)

public class CaffeineCacheTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheAnnotationTester cacheAnnotationTester;

    /**
     * 简单测试CacheManager的功能以及获取的Cache对象的功能
     */
    @Test
    public void caffeineCacheManagerTest() {
        Cache cache = cacheManager.getCache(CacheConstants.R_PAN_CACHE_NAME);
        Assert.notNull(cache);
        cache.put("name", "value");
        String value = cache.get("name", String.class);
        Assert.isTrue("value".equals(value));
    }

    /**
     * 这个测试case，他第一次不会命中缓存因为是第一次加载，第二次会命中缓存
     */
    @Test
    public void caffeineCacheAnnotationTest() {
        for (int i = 0; i < 2; i ++) {
            cacheAnnotationTester.testCacheable("imooc");
        }
    }
}
