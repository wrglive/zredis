### ZRedis
	redis的二次封装, 为了更方便运用, 某些方法,更清晰的使用起来, 
	简化配置操作,  只需要一个配置文件 然后注入即可. 
###  更新:      
    1.0.4 新增Cache注解,基于SPEL语法使用.
    1.0.3 新增一个类RedisLoadingCache<K,V>

### 用法: 
	在resource 
	配置 sky-zredis.properpies      
	@resource 注入command即可使用redis
	
	关于缓存
	使用RedisLoadingCache即可
### 用例： 
#### sky-zredis.properties
	host=
	port=
	db=
	password=

#### 接口层
##### zredis
	@Resource
	IHashRedisCommand hash表
	IKeyRedisCommand key的操作
	IListRedisCommand list操作
	ISetRedisCommand set操作
	ISortedSetRedisCommand 有序set
	IStringRedisCommand 字符串kv

	注入进去就可以直接用了。启动自己会注入到bean中。
	
##### 关于缓存     
	RedisLoadingCache<String, String> skuPostLoadingCache = RedisLoadingCache.<String, String>builder()
    .expireTime(30) //过期时间
    .expireTimeUnit(TimeUnit.SECONDS) //时间类型
    .keyPrefix("SKU.CACHE.TEST:") //key前缀
    .returnClass(String.class) //返回值类型
    .loader(this::test) //如果缓存为null 去执行哪个方法获取数据
    .defaultIfNull("") //如果loader也为null  给的默认值, 不是null就取它 防止缓存穿透
    .readRefreshExpireTime(true) //读取缓存的时候是否刷新过期时间
    .build();
    
###### @ZZCache, ZZCacheDelete     
     (Spring 有个通病, 同一个类或者同一个方法执行多个aop注解的时候会有失效的情况, 后续会关注解决办法)
     包含缓存, 删除缓存的方法, 根据keyPrefix,keyId拼装 唯一的key值, 注意     
     keyId需要传SPEL语法, 例如:     
        
     
    ```
     @ZCache(keyPrefix = "testZZCache", keyId = {"#productModuleVO.spuId","#logInfo"}, expireTimeUnit = TimeUnit.HOURS, expireTime = 1L)     
     public Date test(ProductModuleVO productModuleVO, String logInfo) {     
     return new Date();     
     }     
    ```

### 目前待解决
      1.由于是第二版, 可能存在一些方法的遗漏，并且这些方法是在工作中逐渐整理的, 不排除有一些冷门方法存在bug，需要进行大量测试,不过目前感觉还是稳。
      
      
### 未来更新
      1.考虑加入多数据配置。
      2.加入针对不同模式集群的redis结构进行不同处理， 例如redis-cluster模式不宜用 mget
      3.加入新的接口, 例如pipepine、 execute方法。
      4.以后会加入新的模块，例如 分布式锁， guava 类似的 cache。
      5.根据环境控制数据源。
