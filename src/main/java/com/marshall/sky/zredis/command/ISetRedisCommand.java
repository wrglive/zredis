package com.marshall.sky.zredis.command;

import java.util.List;
import java.util.Set;

public interface ISetRedisCommand {

  Long add(final String key, final String... members);

  Long add(final String key, final List<String> members);

  Long length(final String key);

  @Deprecated
  Set<String> diff(final String... keys);

  @Deprecated
  Long diffStore(final String newKey, final String... keys);

  @Deprecated
  Set<String> intersection(final String... keys);

  @Deprecated
  Long intersectionStore(final String newKey, final String... keys);

  Boolean isMember(final String key, final String member);

  Set<String> members(final String key);

  @Deprecated
  Long move(final String srckey, final String dstkey, final String member);

  String randomPop(final String key);

  Set<String> randomPop(final String key, final long count);

  String randomSelect(final String key);

  List<String> randomSelect(final String key, final int count);

  Long remove(final String key, final String... members);

  Long remove(final String key, final List<String> members);

  @Deprecated
  Set<String> union(final String... keys);

  @Deprecated
  Long unionStore(final String dstKey, final String... keys);

}
