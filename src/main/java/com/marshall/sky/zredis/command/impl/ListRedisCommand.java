package com.marshall.sky.zredis.command.impl;

import com.marshall.sky.zredis.command.IListRedisCommand;
import java.util.List;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class ListRedisCommand implements IListRedisCommand {

  @Override
  @Deprecated
  public List<String> leftPopWithBlocking(int timeout, String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.blpop(timeout, keys);
    }
  }

  @Override
  public String leftPopWithBlocking(int timeout, String key) {
    return Optional.ofNullable(leftPopWithBlocking(timeout, new String[]{key}))
        .map(list -> list.get(1))
        .orElse(null);
  }

  @Override
  @Deprecated
  public List<String> rightPopWithBlocking(int timeout, String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.brpop(timeout, keys);
    }
  }

  @Override
  public String rightPopWithBlocking(int timeout, String key) {
    return Optional.ofNullable(rightPopWithBlocking(timeout, new String[]{key}))
        .map(list -> list.get(1))
        .orElse(null);
  }

  @Override
  public String indexFromLeft(String key, long index) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lindex(key, index);
    }
  }

  @Override
  public Long length(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.llen(key);
    }
  }

  @Override
  public String leftPop(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lpop(key);
    }
  }

  @Override
  public Long leftPush(String key, String... strings) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lpush(key, strings);
    }
  }

  @Override
  public Long leftPush(String key, List<String> values) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = values.toArray(new String[0]);
      return jedis.lpush(key, strings);
    }
  }

  @Override
  public Long leftPushIfExist(String key, String... strings) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lpushx(key, strings);
    }
  }

  @Override
  public Long leftPushIfExist(String key, List<String> values) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = values.toArray(new String[0]);
      return jedis.lpushx(key, strings);
    }
  }

  @Override
  public List<String> range(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lrange(key, start, end);
    }
  }

  @Override
  public Long removeFromLeft(String key, long count, String value) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lrem(key, count, value);
    }
  }

  @Override
  public String setFromLeft(String key, long index, String value) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.lset(key, index, value);
    }
  }

  @Override
  public String trimFromLeft(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.ltrim(key, start, end);
    }
  }

  @Override
  public String rightPop(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.rpop(key);
    }
  }

  @Override
  @Deprecated
  public String rightPopLeftPush(String srckey, String dstkey) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.rpoplpush(srckey, dstkey);
    }
  }

  @Override
  public Long rightPush(String key, String... strings) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.rpush(key, strings);
    }
  }

  @Override
  public Long rightPush(String key, List<String> values) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = values.toArray(new String[values.size()]);
      return jedis.rpush(key, strings);
    }
  }

  @Override
  public Long rightPushIfExist(String key, String... strings) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.rpushx(key, strings);
    }
  }

  @Override
  public Long rightPushIfExist(String key, List<String> values) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = values.toArray(new String[values.size()]);
      return jedis.rpushx(key, strings);
    }
  }

}
