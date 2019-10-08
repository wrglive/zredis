package com.marshall.sky.zredis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HashZRedisCommand {

  Long delete(final String key, final String... fields);

  Long delete(final String key, final List<String> fields);

  Boolean exists(final String key, final String field);

  String get(final String key, final String field);

  Map<String, String> getAllFields(final String key);

  Long incrBy(final String key, final String field, final long value);

  Double incrByDouble(final String key, final String field, final double value);

  Set<String> fieldsKey(final String key);

  List<String> fieldsValue(final String key);

  Long length(final String key);

  List<String> multiGet(final String key, final String... fields);

  List<String> multiGet(final String key, final List<String> fields);

  void multiSet(final String key, final Map<String, String> hash);

  void set(final String key, final String field, final String value);

  void setIfNotExist(final String key, final String field, final String value);

}
