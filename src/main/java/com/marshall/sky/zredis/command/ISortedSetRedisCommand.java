package com.marshall.sky.zredis.command;

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

public interface ISortedSetRedisCommand {

  Long add(final String key, final double score, final String member);

  Long add(final String key, final Map<String, Double> scoreMembers);

  Long length(final String key);

  Long count(final String key, final double min, final double max);

  Double incrScoreBy(final String key, final double score, final String member);

  Set<String> rangeByIndex(final String key, final long start, final long end);

  Set<Tuple> rangeByIndexWithScores(final String key, final long start, final long end);

  Set<String> rangeByScore(final String key, final double min, final double max);

  Set<String> rangeByScore(final String key, final double min, final double max, final int offset,
      final int count);

  Set<Tuple> rangeByScoreWithScores(final String key, final double min, final double max);

  Set<Tuple> rangeByScoreWithScores(final String key, final double min, final double max,
      final int offset, final int count);

  Long rank(final String key, final String member);

  Set<String> reverseRangeByIndex(final String key, final long start, final long end);

  Set<Tuple> reverseRangeByIndexWithScores(final String key, final long start, final long end);

  Set<String> reverseRangeByScore(final String key, final double min, final double max);

  Set<String> reverseRangeByScore(final String key, final double min, final double max,
      final int offset, final int count);

  Set<Tuple> reverseRangeByScoreWithScores(final String key, final double min, final double max);

  Set<Tuple> reverseRangeByScoreWithScores(final String key, final double min, final double max,
      final int offset, final int count);

  Long reverseRank(final String key, final String member);

  Long remove(final String key, final String... members);

  Long removeRangeByRank(final String key, final long start, final long end);

  Long removeRangeByScore(final String key, final double start, final double end);

  Double score(final String key, final String member);

  @Deprecated
  Long unionStore(final String dstkey, final String... sets);

  @Deprecated
  Long unionStore(final String dstkey, final ZParams params, final String... sets);

}
