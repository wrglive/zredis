package com.marshall.sky.zredis.command.impl;

import com.marshall.sky.zredis.command.ISetRedisCommand;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class SetRedisCommand implements ISetRedisCommand {

  @Override
  public Long add(String key, String... members) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sadd(key, members);
    }
  }

  @Override
  public Long add(String key, List<String> members) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = members.toArray(new String[members.size()]);
      return jedis.sadd(key, strings);
    }
  }

  @Override
  public Long length(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.scard(key);
    }
  }

  @Override
  @Deprecated
  public Set<String> diff(String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sdiff(keys);
    }
  }

  @Override
  @Deprecated
  public Long diffStore(String newKey, String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sdiffstore(newKey, keys);
    }
  }

  @Override
  @Deprecated
  public Set<String> intersection(String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sinter(keys);
    }
  }

  @Override
  @Deprecated
  public Long intersectionStore(String newKey, String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sinterstore(newKey, keys);
    }
  }

  @Override
  public Boolean isMember(String key, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sismember(key, member);
    }
  }

  @Override
  public Set<String> members(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.smembers(key);
    }
  }

  @Override
  @Deprecated
  public Long move(String srckey, String dstkey, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.smove(srckey, dstkey, member);
    }
  }

  @Override
  public String randomPop(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.spop(key);
    }
  }

  @Override
  public Set<String> randomPop(String key, long count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.spop(key, count);
    }
  }

  @Override
  public String randomSelect(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.srandmember(key);
    }
  }

  @Override
  public List<String> randomSelect(String key, int count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.srandmember(key, count);
    }
  }

  @Override
  public Long remove(String key, String... members) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.srem(key, members);
    }
  }

  @Override
  public Long remove(String key, List<String> members) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      String[] strings = members.toArray(new String[members.size()]);
      return jedis.srem(key, strings);
    }
  }

  @Override
  @Deprecated
  public Set<String> union(String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sunion(keys);
    }
  }

  @Override
  @Deprecated
  public Long unionStore(String dstkey, String... keys) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.sunionstore(dstkey, keys);
    }
  }

}
