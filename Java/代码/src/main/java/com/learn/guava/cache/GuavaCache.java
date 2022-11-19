package com.learn.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 用于将数据缓存到JVM内存中
 *
 * @author xzy.xiao
 * @date 2022/11/19  19:12
 */
public class GuavaCache {
    private final static Cache<String, Object> STRING_OBJECT_CACHE = CacheBuilder.newBuilder()
            // 并发级别
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            // 初试缓存容量
            .initialCapacity(100)
            // 最大存储空间
            .maximumSize(1000)
            // 缓存失效策略
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws InterruptedException {
        STRING_OBJECT_CACHE.put("key", "Hello World!");

        for (int i = 0; i < 15; i++) {
            Object cache = STRING_OBJECT_CACHE.getIfPresent("key");
            if (cache != null) {
                System.out.println("缓存存在：" + cache);
            } else {
                System.out.println("缓存不存在！");
            }
            Thread.sleep(1000);
        }

    }
}
