package com.marshall.sky.zredis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 执行方法后清除缓存
 *
 * @author wangruigang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZCacheDelete {

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
     * 执行切面时机
     * @return
     */
    ActionType doActionType() default ActionType.AFTER;


    enum ActionType {
        BEFORE,
        AFTER,
        ;
    }
}
