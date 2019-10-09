package com.marshall.sky.zredis.model;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RedisCache<K, V> {

    @NonNull
    String keyPrefix;
    @NonNull
    Function<K, V> loader;
    @NonNull
    TimeUnit expireTimeUnit;
    @NonNull
    long expireAt;
    @NonNull
    Class<V> returnClass;
    V defaultIfNull;

}
