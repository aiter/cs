### 架构
server
* 连接器
  * 链接管理，权限验证
* 缓存
* 分析器
  * 词法分析，语法分析
* 优化器
  * 执行计划，索引选择
* 执行器
  * 操作引擎，返回结果

engine
* innodb
* myIsam

#### 日志
* binlog 是server的日志
* 不同的引擎可能也有日志
   * innodb 的redo log
