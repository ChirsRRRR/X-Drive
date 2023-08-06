package com.imooc.pan.cache.caffeine.test.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Caffeine Cache 自定义配置属性类
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.imooc.pan.cache.caffeine")
public class CaffeineCacheProperties {

    /**
     * 缓存初始容量
     * com.imooc.pan.cache.caffeine。init-cache-capacity
     */
    private Integer initCacheCapacity = 256;

    /**
     * 缓存再最大容量，超过最大容量之后，会按照recently or very frequently used (最近最少策略)进行缓存删除
     * com.imooc.pan.cache.caffeine.max-cache-capacity
     */
    private Long maxCacheCapacity = 10000L;

    /**
     * 是否允许空值null作为缓存的value
     * com.imooc.pan.cache.caffeine.allow-null-values
     */
    private Boolean allowNullValues = Boolean.TRUE;
}
