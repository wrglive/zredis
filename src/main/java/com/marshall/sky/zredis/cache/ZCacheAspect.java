package com.marshall.sky.zredis.cache;

import com.alibaba.fastjson.JSONObject;
import com.marshall.sky.zredis.annotation.ZCache;
import com.marshall.sky.zredis.command.impl.KeyRedisCommand;
import com.marshall.sky.zredis.command.impl.StringRedisCommand;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 切面类
 * 目前这个实现方式跟 RedisLoadingCache是一致的， 以后为了维护， 考虑抽到一起去
 *
 * @author wangruigang
 */
@Component
@Aspect
@Slf4j
public class ZCacheAspect {

    @Autowired
    private StringRedisCommand redisCommand;
    @Autowired
    private KeyRedisCommand keyRedisCommand;


    @Pointcut(value = "@annotation(com.marshall.sky.zredis.annotation.ZCache)")
    public void execute() {
    }


    @Around(value = "execute()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(ZCache.class)) {
            return proceedingJoinPoint.proceed();
        }
        //get annotation Args
        ZCache annotation = method.getAnnotation(ZCache.class);
        boolean readRefreshExpireTime = annotation.readRefreshExpireTime();
        String keyPrefix = annotation.keyPrefix();
        long expireTime = annotation.expireTime();
        TimeUnit expireTimeUnit = annotation.expireTimeUnit();
        Class returnType = signature.getReturnType();

        //TODO: id的取法以及算法需要换一种通用的 可以适配的方式, 不应该只用args的某一个索引, 万一是对象呢?
        //获取id
        String id = CacheIdUtil.buildKeyId(proceedingJoinPoint.getArgs(), parameterNames, annotation.keyId());
        String redisKey = keyPrefix + id;
        String json = redisCommand.get(redisKey);
        if (json != null) {
            if (readRefreshExpireTime) {
                expireCache(redisKey, expireTime, expireTimeUnit);
            }
            return JSONObject.parseObject(json, returnType);
        }
        Object value = proceedingJoinPoint.proceed();
        if (value == null) {
            return null;
        }
        redisCommand.set(redisKey, JSONObject.toJSONString(value));
        expireCache(redisKey, expireTime, expireTimeUnit);
        return value;

    }

    private void expireCache(String redisKey, long expireTime, TimeUnit timeUnit) {
        int expireSeconds = (int) TimeUnit.SECONDS.convert(expireTime, timeUnit);
        keyRedisCommand.expire(redisKey, expireSeconds);
    }
}
