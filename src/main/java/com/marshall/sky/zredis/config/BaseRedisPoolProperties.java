package com.marshall.sky.zredis.config;

public class BaseRedisPoolProperties {

  // 最大连接数
  private static final int DEFAULT_MAX_TOTAL = 256;
  // 最大空闲数
  private static final int DEFAULT_MAX_IDLE = 32;
  // 最小空闲数
  private static final int DEFAULT_MIN_IDLE = 8;
  // 最大等待时间
  private static final int DEFAULT_MAX_WAIT_MILLIS = 10000;
  // 是否测试连接
  private static final boolean DEFAULT_TEST_ON_BORROW = true;


  public static int getDefaultMaxTotal() {
    return DEFAULT_MAX_TOTAL;
  }

  public static int getDefaultMaxIdle() {
    return DEFAULT_MAX_IDLE;
  }

  public static int getDefaultMinIdle() {
    return DEFAULT_MIN_IDLE;
  }

  public static int getDefaultMaxWaitMillis() {
    return DEFAULT_MAX_WAIT_MILLIS;
  }

  public static boolean isDefaultTestOnBorrow() {
    return DEFAULT_TEST_ON_BORROW;
  }
}
