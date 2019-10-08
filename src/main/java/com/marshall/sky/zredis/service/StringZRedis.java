package com.marshall.sky.zredis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

public class StringZRedis implements StringZRedisCommand {

  @Override
  public Long append(String key, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.append(key, value);
    }
  }

  @Override
  public Long decr(String key) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.decr(key);
    }
  }

  @Override
  public String get(String key) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.get(key);
    }
  }

  @Override
  public String getRange(String key, long startOffset, long endOffset) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.getrange(key, startOffset, endOffset);
    }
  }

  @Override
  public String getOldValueAndSet(String key, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.getSet(key, value);
    }
  }

  @Override
  public Long incr(String key) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.incr(key);
    }
  }

  @Override
  public Long incrBy(String key, long integer) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.incrBy(key, integer);
    }
  }

  @Override
  public Double incrByDouble(String key, double value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.incrByFloat(key, value);
    }
  }

  @Override
  public List<String> multiGetAsList(String... keys) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.mget(keys);
    }
  }

  @Override
  public Map<String, String> multiGetAsMap(String... keys) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      List<String> list = jedis.mget(keys);
      HashMap<String, String> map = new HashMap<>(list.size());
      for (int index = 0; index < keys.length; index++) {
        map.put(keys[index], list.get(index));
      }
      return map;
    }
  }

  @Override
  public void multiSet(String... keysValues) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      jedis.mset(keysValues);
    }
  }

  @Override
  public void multiSet(Map<String, String> kvMap) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      if (kvMap == null || kvMap.isEmpty()) {
        return;
      }
      int len = kvMap.size();
      String[] keys = kvMap.keySet().toArray(new String[0]);
      String[] keysValues = new String[len * 2];

      for (int index = 0; index < len; index++) {
        keysValues[2 * index] = keys[index];
        keysValues[2 * index + 1] = kvMap.get(keys[index]);
      }
      jedis.mset(keysValues);
    }
  }

  @Override
  public void set(String key, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      jedis.set(key, value);
    }
  }

  @Override
  public void setWithExpire(String key, int seconds, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      jedis.setex(key, seconds, value);
    }
  }

  @Override
  public Long setIfNotExist(String key, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.setnx(key, value);
    }
  }

  @Override
  public boolean setIfNotExistWithExpire(String key, String value, int seconds) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.set(key, value, "nx", "ex", seconds) != null;
    }
  }

  @Override
  public Long setRange(String key, long offset, String value) {
    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.setrange(key, offset, value);
    }
  }

  @Override
  public Long getLength(String key) {

    try (Jedis jedis = BaseZRedis.getRedis()) {
      return jedis.strlen(key);
    }
  }
}
