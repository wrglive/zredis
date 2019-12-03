package com.marshall.sky.zredis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author wangruigang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZCache {

    /**
     * 前缀
     *
     * @return
     */
    String keyPrefix();

    /**
     * 组装key 的id 来源是args入参索引
     *
     * @return
     */
    String[] keyId() default {};

    /**
     * 时间戳单位
     *
     * @return
     */
    TimeUnit expireTimeUnit() default TimeUnit.DAYS;

    /**
     * 过期时间
     *
     * @return
     */
    long expireTime() default 2;

    /**
     * 读取缓存的时候是否重新刷新一遍过期时间
     *
     * @return
     */
    boolean readRefreshExpireTime() default false;
}
