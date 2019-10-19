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

#  日志系统：一条更新SQL是如何执行的？
* redo log（重做日志）
* binlog（归档日志）

>  WAL技术，Write Ahead Logging，先写日志+内存，再写磁盘。有了这种能力，就算服务器重启，数据也不会丢失，这叫做crash-safe
 
*Innodb* 先写到redo log，并更新内存，这个时候就算完成了。在适当的时候将操作记录更新到磁盘
*myisam* 是没有crash-safe能力的


![](media/15714977642334.png)
 
 **两阶段提交** redolog先写为`prepare`，server层再写binlong，提交事务在改为`commit`
 
 redolog：宕机恢复，可以保证数据的正确性
 binlog：恢复数据，保证逻辑过程

