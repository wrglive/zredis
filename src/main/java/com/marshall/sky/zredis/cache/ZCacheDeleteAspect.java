package com.marshall.sky.zredis.cache;

import com.marshall.sky.zredis.annotation.ZCacheDelete;
import com.marshall.sky.zredis.command.IKeyRedisCommand;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面类
 * 目前这个实现方式跟 RedisLoadingCache是一致的， 以后为了维护， 考虑抽到一起去
 *
 * @author wangruigang
 */
@Component
@Aspect
@Slf4j
public class ZCacheDeleteAspect {
    @Autowired
    private IKeyRedisCommand keyRedisCommand;

    @Pointcut(value = "@annotation(com.marshall.sky.zredis.annotation.ZCacheDelete)")
    public void execute() {
    }

    @Around(value = "execute()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(ZCacheDelete.class)) {
            proceedingJoinPoint.proceed();
            return;
        }
        //get annotation Args
        ZCacheDelete zzCacheDelete = method.getAnnotation(ZCacheDelete.class);
        String keyPrefix = zzCacheDelete.keyPrefix();
        String id = CacheIdUtil.buildKeyId(proceedingJoinPoint.getArgs(), parameterNames, zzCacheDelete.keyId());
        ZCacheDelete.ActionType actionType = zzCacheDelete.doActionType();
        if (actionType == ZCacheDelete.ActionType.AFTER) {
            proceedingJoinPoint.proceed();
            keyRedisCommand.delete(keyPrefix + id);
            return;
        }
        keyRedisCommand.delete(keyPrefix + id);
        proceedingJoinPoint.proceed();
    }
}
