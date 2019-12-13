### 使用场景
* database 数据库
* cache  缓存
* message broker 

### 支持的数据结构
* strings KV
* hashes  hash
* lists  
* sets
* sorted sets WITH range queries
* bitmaps
* hyperloglogs
* geospatial indexes WITH radius queries
* streams

### 内置特性
* replication  备份复制
* Lua scripting Lua脚本支持
* LRU eviction  LRU淘汰算法
* transactions  事务
* different levels of on-disk persistence  不同级别的持久化
* provides high availability 高可用
> * Sentinel  哨兵
> * Cluster 可以 automatic partitioning  集群

#### Sentinel 哨兵
> * master: 主节点
> * replica: 从节点 
 * Monitoring: 监控所有的节点(master and replica)
 * Notification: 如果监控发现问题，可以通过API，通知系统管理员或者其他程序
 * Automatic failover: 自动故障切换。如果master故障，将选举一个replica为新的master，其他的replica也会重新配置新master，client的新链接也会连接新的master
 * Configuration provider: 配置管理、服务返现。发现新的master地址，返回给client

 ##### Sentinel 分布式系统
 * 多个Sentinel实例进程
 * 可以快速的检测到故障的master
 * Sentinel本身高可用的，不会有单点问题

 > Sentinel 2. 使用更加强壮、简单可预测的算法（？？？）

 ##### 启动方式
 ```shell
   # 1 使用redis-sentinel
   redis-sentinel /path/to/sentinel.conf
   # 2 直接使用redis-server
   redis-server /path/to/sentinel.conf --sentinel
 ```
 * 默认监听  26379端口
 * 部署sentinel的注意事项
 > * 至少3个sentienl实例;分别部署到不同的物理服务器或者虚拟机中()
 > * sentinel+redis的分布式方案，不能保证故障期间的写请求，因为redis使用异步复制。但是也有方式是写丢失限制在很小的时间范围，但是这种部署不太安全。
 > * redis的client必须支持。大多数的client都支持
 > * 需要在开发环境不断的测试验证，才能真正的有高可用方案
 > * 使用docker或其他形式的NAT(Network Address Translation)或端口映射(Port Mappging)需要格外小心：docker可能破坏其他sentinel实例、master副本的自动发现。

 ##### 自带实例
 ```shell
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 60000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1

sentinel monitor resque 192.168.1.3 6380 4
sentinel down-after-milliseconds resque 10000
sentinel failover-timeout resque 180000
sentinel parallel-syncs resque 5
 ```
 * 上面的配置，监控了2个集群（每个集群由1个master和N个replica组成）
 * 只需要指定监控的master实例
 * 每个master设置不同的名字
 * 不需要指定replica副本，会自动发现
 * sentinel会自动更新这个配置文件
 * 每次切换master也会被更新
```shell
sentinel monitor <master-group-name> <ip> <port> <quorum>
```

### 复制
* 主节点(master)发送commands给副本(replica)
* 主从断开链接后，再重新链接，会同步未同步的部分(那些没有同步的commands). 什么机制来做到的呢？和mysql的方式有什么异同
* 如果部分同步不成功，那么会请求全量同步。master会把数据打一个快照发送给replica，然后再发送实时更新的commands。
*
* 默认使用异步复制，支持同步复制
> 复制和高可用方案容易混为一谈。

* 可以一主多副本
* 副本也可以直接连接另一个副本，就想和连接master一样
* 复制在master上是非阻塞的。
* 复制在replica上也是非阻塞的。
* 方便横向扩展、数据安全、高可用