package com.marshall.sky.zredis;

import java.io.FileReader;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public abstract class BaseRedis {

  protected static volatile JedisPool jedisPool = null;

  protected BaseRedis(){}

  public static void init(String confPath) throws Exception {
    if (jedisPool == null) {
      Class var1 = BaseRedis.class;
      synchronized (BaseRedis.class) {
        if (jedisPool == null) {
          Properties properties = new Properties();
          properties.load(new FileReader(confPath));
          String host = properties.getProperty("host", "");
          Integer port = Integer.valueOf(properties.getProperty("port", "0"));
          String password = properties.getProperty("password", "");
          Integer db =Integer.valueOf(properties.getProperty("db", "0"));
          JedisPoolConfig config = new JedisPoolConfig();
          // 获取jedis实例是最多等待的时间
          config.setMaxWaitMillis(10000);
          // 在去连接时是否判断可用有效
          config.setTestOnBorrow(true);
          //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
          config.setTestOnReturn(true);
          jedisPool = new JedisPool(config, host, port, 5000,
              password);
        }
      }
    }
  }

  protected static Jedis getJedis() {
    if (jedisPool == null) {
      throw new RuntimeException("jedis has not init success");
    } else {
      Jedis jedis = jedisPool.getResource();
      if (!jedis.isConnected()) {
        releaseJedis(jedis);
        jedis = jedisPool.getResource();
      }

      return jedis;
    }
  }

  protected static void releaseJedis(Jedis jedis) {
    try {
      if (jedis != null) {
        jedis.close();
      }

    } catch (Exception var2) {
      jedis.close();
      throw new RuntimeException("{PrivateRedisClient}{releaseJedis} error", var2);
    }
  }

}
