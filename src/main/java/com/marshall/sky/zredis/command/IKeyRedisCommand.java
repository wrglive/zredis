package com.marshall.sky.zredis.command;

import java.util.List;
import java.util.Set;

public interface IKeyRedisCommand {

  Long delete(String key);

  @Deprecated
  Long delete(final String... keys);

  @Deprecated
  Long delete(final List<String> keys);

  Boolean exists(final String key);

  @Deprecated
  Long exists(final String... keys);

  @Deprecated
  Long exists(final List<String> keys);

  Long expire(final String key, final int seconds);

  Long expireAt(final String key, final long unixTime);

  Long expireInMilliseconds(final String key, final long milliseconds);

  Long expireAtInMilliseconds(final String key, final long millisecondsTimestamp);

  @Deprecated
  Set<String> keys(final String pattern);

  Long persist(final String key);

  Long ttl(final String key);

  Long ttlInMilliseconds(final String key);

  @Deprecated
  String randomKey();

  @Deprecated
  String rename(final String oldkey, final String newkey);

  @Deprecated
  Long renameNewkeyNotExist(final String oldkey, final String newkey);

}
