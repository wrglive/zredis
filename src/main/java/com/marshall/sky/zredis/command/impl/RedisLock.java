package com.marshall.sky.zredis.command.impl;

import com.marshall.sky.zredis.utils.ZRedisBeanUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * 建议的redis锁
 *
 * @author wangruigang
 * @version 1.0
 * @classname RedisLock
 * @date 2020/3/17 17:55
 */
@Slf4j
public class RedisLock<T> {

    private final StringRedisCommand stringRedisCommand = ZRedisBeanUtil.getObject(StringRedisCommand.class);
    private final KeyRedisCommand keyRedisCommand = ZRedisBeanUtil.getObject(KeyRedisCommand.class);

    /**
     * 锁名称
     */
    private final String lockName;

    /**
     * 锁抢占失败,等待下一次抢占的时间
     */
    private final int waitInterval;

    /**
     * 锁占用失败超时时间
     */
    private final int waitTimeout;

    /**
     * Redis锁过期的时间
     */
    private final int expireSeconds;

    /**
     * logStr
     */
    private final String logStr;


    public RedisLock(String lockName, int waitInterval, int waitTimeout, int expireSeconds, String logStr) {
        this.lockName = lockName;
        this.waitInterval = waitInterval;
        this.waitTimeout = waitTimeout;
        this.expireSeconds = expireSeconds;
        this.logStr = logStr;
    }

    /**
     * 加锁获取数据
     *
     * @param loader
     * @return
     */
    public T lock(Supplier<T> loader) {
        String uuid = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        // 同步块减少单应用内的争抢
        synchronized (lockName) {
            try {
                while (true) {
                    // 因为同步块也会增加等待时间, 所以在每次加锁开始时判断
                    if (System.currentTimeMillis() > startTime + waitTimeout) {
                        throw new RuntimeException(logStr + ", redis lock timeout");
                    }
                    boolean locked = tryLock(uuid);
                    if (locked) {
                        return loader.get();
                    }
                    try {
                        lockName.wait(waitInterval);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                unLock(uuid);
                log.info("lock info. name: " + lockName + " time: " + (System.currentTimeMillis() - startTime) + "ms");
            }
        }
    }

    private boolean tryLock(String val) {
        try {
            return stringRedisCommand.setIfNotExistWithExpire(lockName, val, expireSeconds);
        } catch (Exception e) {
            log.error("redis commands failed.", e);
            return false;
        }
    }

    private void unLock(String val) {
        try {
            if (val.equals(stringRedisCommand.get(lockName))) {
                keyRedisCommand.delete(lockName);
            }
        } catch (Exception e) {
            log.error("redis commands failed.", e);
        }
    }

}
