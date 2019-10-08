package com.marshall.sky.zredis.service;

import com.marshall.sky.zredis.config.BaseRedisPoolProperties;
import java.io.InputStream;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public abstract class BaseRedis {

  protected static volatile JedisPool jedisPool = null;

  protected BaseRedis() {
  }

  protected static void init() {
    if (jedisPool == null) {
      synchronized (BaseRedis.class) {
        if (jedisPool == null) {
          Properties properties = getProperties();
          String host = properties.getProperty("host", "");
          Integer port = Integer.valueOf(properties.getProperty("port", "0"));
          String password = properties.getProperty("password", null);
          Integer db = Integer.valueOf(properties.getProperty("db", "0"));
          JedisPoolConfig config = new JedisPoolConfig();
          // 获取redis实例是最多等待的时间
          config.setMaxWaitMillis(BaseRedisPoolProperties.getDefaultMaxWaitMillis());
          // 在去连接时是否判断可用有效
          config.setTestOnBorrow(true);
          //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
          config.setTestOnReturn(true);
          jedisPool = new JedisPool(config, host, port, 5000,
              password, db);
        }
      }
    }
  }

  protected static Jedis getRedis() {
    if (jedisPool == null) {
      BaseRedis.init();
    }
    Jedis jedis = jedisPool.getResource();
    if (!jedis.isConnected()) {
      releaseRedis(jedis);
      jedis = jedisPool.getResource();
    }
    return jedis;
  }

  protected static void releaseRedis(Jedis jedis) {
    try {
      if (jedis != null) {
        jedis.close();
      }

    } catch (Exception var2) {
      jedis.close();
      throw new RuntimeException("{PrivateRedisClient}{releaseJedis} error", var2);
    }
  }

  private static Properties getProperties() {
    Properties properties = new Properties();
    try {
      InputStream inputStream = InitRegistryProcessor.class.getClassLoader()
          .getResourceAsStream("sky-zredis.properties");
      properties.load(inputStream);
    } catch (Exception e) {
      throw new RuntimeException("load sky-zredis.properties error!");
    }
    return properties;
  }
}
