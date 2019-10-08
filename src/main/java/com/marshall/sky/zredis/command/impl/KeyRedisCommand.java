package com.marshall.sky.zredis.command.impl;

import com.marshall.sky.zredis.command.IKeyRedisCommand;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class KeyRedisCommand implements IKeyRedisCommand {

  @Override
  public Long delete(String key) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.del(key);
    }
  }

  @Override
  public Long delete(String... keys) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.del(keys);
    }
  }

  @Override
  public Long delete(List<String> keys) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      String[] strings = keys.toArray(new String[0]);
      return jedis.del(strings);
    }
  }

  @Override
  public Boolean exists(String key) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.exists(key);
    }
  }

  @Override
  public Long exists(String... keys) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.exists(keys);
    }
  }

  @Override
  public Long exists(List<String> keys) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      String[] strings = keys.toArray(new String[0]);
      return jedis.exists(strings);
    }
  }

  @Override
  public Long expire(String key, int seconds) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.expire(key, seconds);
    }
  }

  @Override
  public Long expireAt(String key, long unixTime) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.expireAt(key, unixTime);
    }
  }

  @Override
  public Long expireInMilliseconds(String key, long milliseconds) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.pexpire(key, milliseconds);
    }
  }

  @Override
  public Long expireAtInMilliseconds(String key, long millisecondsTimestamp) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.pexpireAt(key, millisecondsTimestamp);
    }
  }

  @Override
  public Set<String> keys(String pattern) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.keys(pattern);
    }
  }

  @Override
  public Long persist(String key) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.persist(key);
    }
  }

  @Override
  public Long ttl(String key) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.ttl(key);
    }
  }

  @Override
  public Long ttlInMilliseconds(String key) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.pttl(key);
    }
  }

  @Override
  public String randomKey() {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.randomKey();
    }
  }

  @Override
  public String rename(String oldkey, String newkey) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.rename(oldkey, newkey);
    }
  }

  @Override
  public Long renameNewkeyNotExist(String oldkey, String newkey) {
    try (Jedis jedis =RedisPoolFactory.getRedis()) {
      return jedis.renamenx(oldkey, newkey);
    }
  }
}
