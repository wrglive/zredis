# zredis
redis的二次封装
### 用法:    
      在resource中配置sky-zredis.properpies
      配置文件目前有 host post db password
      然后@Resource @Autowired 注入 StringRedis、HashRedis等即可使用.
      极简化配置。
### 用例：   
#### sky-zredis.properties
      host=localhost
      port=6379
      db=0
      password=
      
#### 接口层
      @Resource
      IHashRedisCommand hash表
      IKeyRedisCommand  key的操作
      IListRedisCommand list操作
      ISetRedisCommand  set操作
      ISortedSetRedisCommand  有序set
      IStringRedisCommand     字符串kv
      
      注入进去就可以直接用了。启动自己会注入到bean中。
      
### 目前待解决
      1.由于是第一版, 可能存在一些方法的遗漏，并且这些方法是在工作中逐渐整理的, 不排除有一些冷门方法存在bug，需要进行大量测试,不过目前感觉还是稳。
      
      
### 未来更新
      1.考虑加入多数据配置。
      2.加入针对不同模式集群的redis结构进行不同处理， 例如redis-cluster模式不宜用 mget
      3.加入新的接口, 例如pipepine、 execute方法。
      4.以后会加入新的模块，例如 分布式锁， guava 类似的 cache。
      
