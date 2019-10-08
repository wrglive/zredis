package com.marshall.sky.zredis.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

public class StringRedis {

  public Long append(String key, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.append(key, value);
    }
  }


  public Long decr(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.decr(key);
    }
  }


  public String get(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.get(key);
    }
  }


  public String getRange(String key, long startOffset, long endOffset) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.getrange(key, startOffset, endOffset);
    }
  }


  public String getOldValueAndSet(String key, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.getSet(key, value);
    }
  }


  public Long incr(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.incr(key);
    }
  }


  public Long incrBy(String key, long integer) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.incrBy(key, integer);
    }
  }


  public Double incrByDouble(String key, double value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.incrByFloat(key, value);
    }
  }


  public List<String> multiGetAsList(String... keys) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.mget(keys);
    }
  }


  public Map<String, String> multiGetAsMap(String... keys) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      List<String> list = jedis.mget(keys);
      HashMap<String, String> map = new HashMap<>(list.size());
      for (int index = 0; index < keys.length; index++) {
        map.put(keys[index], list.get(index));
      }
      return map;
    }
  }


  public void multiSet(String... keysValues) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.mset(keysValues);
    }
  }


  public void multiSet(Map<String, String> kvMap) {
    try (Jedis jedis = BaseRedis.getRedis()) {
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


  public void set(String key, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.set(key, value);
    }
  }


  public void setWithExpire(String key, int seconds, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.setex(key, seconds, value);
    }
  }


  public Long setIfNotExist(String key, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.setnx(key, value);
    }
  }


  public boolean setIfNotExistWithExpire(String key, String value, int seconds) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.set(key, value, "nx", "ex", seconds) != null;
    }
  }


  public Long setRange(String key, long offset, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.setrange(key, offset, value);
    }
  }


  public Long getLength(String key) {

    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.strlen(key);
    }
  }
}
