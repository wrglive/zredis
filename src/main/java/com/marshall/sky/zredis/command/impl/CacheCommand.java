package com.marshall.sky.zredis.command.impl;

import com.alibaba.fastjson.JSONObject;
import com.marshall.sky.zredis.command.ICacheCommand;
import com.marshall.sky.zredis.command.IKeyRedisCommand;
import com.marshall.sky.zredis.command.IStringRedisCommand;
import com.marshall.sky.zredis.model.RedisCache;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

public class CacheCommand implements ICacheCommand {

  @Resource
  IStringRedisCommand redisCommand;
  @Resource
  IKeyRedisCommand keyRedisCommand;

  @Override
  public <K, V> Optional<V> get(K id, RedisCache<K, V> builder) {
    String redisKey = builder.getKeyPrefix() + id;
    String s = redisCommand.get(redisKey);
    if (s != null) {
      return Optional.ofNullable(JSONObject.parseObject(s, builder.getReturnClass()));
    }
    V value = builder.getLoader().apply(id);
    if (value == null && builder.getDefaultIfNull() == null) {
      return Optional.empty();
    }
    if (value == null) {
      value = builder.getDefaultIfNull();
    }
    putToRedisCache(redisKey, value);
    expireCache(redisKey, builder.getExpireAt(), builder.getExpireTimeUnit());
    return Optional.ofNullable(value);
  }

  @Override
  public <K, V> void destroy(K id, RedisCache<K, V> builder) {
    keyRedisCommand.delete(builder.getKeyPrefix() + id);
  }


  private <V> void putToRedisCache(String redisKey, V value) {
    redisCommand.set(redisKey, JSONObject.toJSONString(value));
    return;
  }

  private void expireCache(String redisKey, long expireAt, TimeUnit timeUnit) {
    int expireSeconds = (int) TimeUnit.SECONDS.convert(expireAt, timeUnit);
    keyRedisCommand.expire(redisKey, expireSeconds);
  }

}
