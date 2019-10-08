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
      
#### 用法
      @Resource
      IHashRedisCommand
      IKeyRedisCommand
      IListRedisCommand
      ISetRedisCommand
      ISortedSetRedisCommand
      IStringRedisCommand
      
      注入进去就可以直接用了。启动自己会注入到bean中。
      
