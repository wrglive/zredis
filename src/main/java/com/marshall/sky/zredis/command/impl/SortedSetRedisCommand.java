package com.marshall.sky.zredis.command.impl;

import com.marshall.sky.zredis.command.ISortedSetRedisCommand;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

public class SortedSetRedisCommand implements ISortedSetRedisCommand {

  @Override
  public Long add(String key, double score, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zadd(key, score, member);
    }
  }

  @Override
  public Long add(String key, Map<String, Double> scoreMembers) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zadd(key, scoreMembers);
    }
  }

  @Override
  public Long length(String key) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zcard(key);
    }
  }

  @Override
  public Long count(String key, double min, double max) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zcount(key, min, max);
    }
  }

  @Override
  public Double incrScoreBy(String key, double score, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zincrby(key, score, member);
    }
  }

  @Override
  public Set<String> rangeByIndex(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrange(key, start, end);
    }
  }

  @Override
  public Set<Tuple> rangeByIndexWithScores(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrangeWithScores(key, start, end);
    }
  }

  @Override
  public Set<String> rangeByScore(String key, double min, double max) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrangeByScore(key, min, max);
    }
  }

  @Override
  public Set<String> rangeByScore(String key, double min, double max, int offset, int count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrangeByScore(key, min, max, offset, count);
    }
  }

  @Override
  public Set<Tuple> rangeByScoreWithScores(String key, double min, double max) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrangeByScoreWithScores(key, min, max);
    }
  }

  @Override
  public Set<Tuple> rangeByScoreWithScores(String key, double min, double max, int offset,
      int count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }
  }

  @Override
  public Long rank(String key, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrank(key, member);
    }
  }

  @Override
  public Set<String> reverseRangeByIndex(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrange(key, start, end);
    }
  }

  @Override
  public Set<Tuple> reverseRangeByIndexWithScores(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrangeWithScores(key, start, end);
    }
  }

  @Override
  public Set<String> reverseRangeByScore(String key, double min, double max) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrangeByScore(key, max, min);
    }
  }

  @Override
  public Set<String> reverseRangeByScore(String key, double min, double max, int offset,
      int count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrangeByScore(key, max, min, offset, count);
    }
  }

  @Override
  public Set<Tuple> reverseRangeByScoreWithScores(String key, double min, double max) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrangeByScoreWithScores(key, max, min);
    }
  }

  @Override
  public Set<Tuple> reverseRangeByScoreWithScores(String key, double min, double max, int offset,
      int count) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }
  }

  @Override
  public Long reverseRank(String key, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrevrank(key, member);
    }
  }

  @Override
  public Long remove(String key, String... members) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zrem(key, members);
    }
  }

  @Override
  public Long removeRangeByRank(String key, long start, long end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zremrangeByRank(key, start, end);
    }
  }

  @Override
  public Long removeRangeByScore(String key, double start, double end) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zremrangeByScore(key, start, end);
    }
  }

  @Override
  public Double score(String key, String member) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zscore(key, member);
    }
  }

  @Override
  public Long unionStore(String dstkey, String... sets) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zunionstore(dstkey, sets);
    }
  }

  @Override
  public Long unionStore(String dstkey, ZParams params, String... sets) {
    try (Jedis jedis = RedisPoolFactory.getRedis()) {
      return jedis.zunionstore(dstkey, params, sets);
    }
  }

}
