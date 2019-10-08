package com.marshall.sky.zredis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class HashRedis implements HashRedisCommand {

  @Override
  public Long delete(String key, String... fields) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hdel(key, fields);
    }
  }

  @Override
  public Long delete(String key, List<String> fields) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      String[] strings = fields.toArray(new String[0]);
      return jedis.hdel(key, strings);
    }
  }

  @Override
  public Boolean exists(String key, String field) {
        try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hexists(key, field);
    }
  }

  @Override
  public String get(String key, String field) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hget(key, field);
    }
  }

  @Override
  public Map<String, String> getAllFields(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hgetAll(key);
    }
  }

  @Override
  public Long incrBy(String key, String field, long value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hincrBy(key, field, value);
    }
  }

  @Override
  public Double incrByDouble(String key, String field, double value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hincrByFloat(key, field, value);
    }
  }

  @Override
  public Set<String> fieldsKey(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hkeys(key);
    }
  }

  @Override
  public List<String> fieldsValue(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hvals(key);
    }
  }

  @Override
  public Long length(String key) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hlen(key);
    }
  }

  @Override
  public List<String> multiGet(String key, String... fields) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      return jedis.hmget(key, fields);
    }
  }

  @Override
  public List<String> multiGet(String key, List<String> fields) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      String[] strings = fields.toArray(new String[0]);
      return jedis.hmget(key, strings);
    }
  }

  @Override
  public void multiSet(String key, Map<String, String> hash) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.hmset(key, hash);
    }
  }

  @Override
  public void set(String key, String field, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.hset(key, field, value);
    }
  }

  @Override
  public void setIfNotExist(String key, String field, String value) {
    try (Jedis jedis = BaseRedis.getRedis()) {
      jedis.hsetnx(key, field, value);
    }
  }

}
