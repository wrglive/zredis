package com.marshall.sky.zredis.command.impl;

import com.alibaba.fastjson.JSONObject;
import com.marshall.sky.zredis.utils.ZRedisBeanUtil;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

@Builder
@AllArgsConstructor
public class RedisLoadingCache<K, V> {


  private final StringRedisCommand redisCommand = ZRedisBeanUtil.getObject(StringRedisCommand.class);
  private final KeyRedisCommand keyRedisCommand = ZRedisBeanUtil.getObject(KeyRedisCommand.class);

  @NonNull
  private String keyPrefix;
  @NonNull
  private Function<K, V> loader;
  @NonNull
  private TimeUnit expireTimeUnit;
  @NonNull
  private long expireTime;
  @NonNull
  private Class<V> returnClass;
  private V defaultIfNull;
  private boolean readRefreshExpireTime;


  public Optional<V> get(K id) {
    String redisKey = keyPrefix + id;
    String json = redisCommand.get(redisKey);
    if (json != null) {
      if (readRefreshExpireTime) {
        expireCache(redisKey, expireTime, expireTimeUnit);
      }
      return Optional.ofNullable(JSONObject.parseObject(json, returnClass));
    }
    V value = loader.apply(id);
    if (value == null && defaultIfNull == null) {
      return Optional.empty();
    }
    if (value == null) {
      value = defaultIfNull;
    }
    putToRedisCache(redisKey, value);
    expireCache(redisKey, expireTime, expireTimeUnit);
    return Optional.ofNullable(value);
  }

  public void destroy(K id) {
    keyRedisCommand.delete(keyPrefix + id);
  }


  private void putToRedisCache(String redisKey, V value) {
    redisCommand.set(redisKey, JSONObject.toJSONString(value));
    return;
  }

  private void expireCache(String redisKey, long expireTime, TimeUnit timeUnit) {
    int expireSeconds = (int) TimeUnit.SECONDS.convert(expireTime, timeUnit);
    keyRedisCommand.expire(redisKey, expireSeconds);
  }
}
