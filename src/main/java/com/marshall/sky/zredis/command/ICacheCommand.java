package com.marshall.sky.zredis.command;

import com.marshall.sky.zredis.model.RedisCache;
import java.util.Optional;

public interface ICacheCommand {

  <K, V> Optional<V> get(K id, RedisCache<K, V> builder);

  <K, V> void destroy(K id, RedisCache<K, V> builder);

}
