#  1. 一条SQL是如何执行的

> Server层+存储引擎层

## Server层
* 连接器
* 查询缓存
* 分析器
* 优化器
* 执行器

> 内置函数（时间/数学/加密）、存储过程、触发器、视图等

## 存储引擎层

> 数据的存储和提取

![](media/15714418442339.png)


### 连接器
* 建立连接、获取权限、维持和管理连接

### 查询缓存

> 建议不要使用，利大于弊

### 分析器
* 词法分析
* 语法分析

>  生成语法树

```
You have an error in your SQL syntax
``` 
### 优化器
* 多个索引，使用索引的选择。 `优化器如何选择索引？`
* jion操作，各表的顺序
* 执行计划生成

### 执行器
* precheck 检查对表的权限
* 使用引擎提供的接口“查询”。`rows_examined`扫描了多少行，但是，有些场景下，执行器执行一次，在引擎内部则扫描了多行，因此引擎扫描行数和`rows_examinded`并不是完成相同

## 问题
>  报不存在某列，是在什么阶段报出来的？

### 答案
>  分析器。说明分析器，就有表结构信息
 
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

#  事务隔离：为什么你改的我看不见？
> 事务是由引擎支持的，innodb支持，myisam不支持

## 事务 ACID
*  Atomicity 原子性
*  Consistency 一致性
*  Isolation 隔离性
*  Durability 持久性

## 多个事务导致的问题
* 脏读 dirty read
* 不可重复读 non-repeatable read
* 幻读 phantom read

## 为了解决这些问题，就需要隔离级别
* 读未提交 read uncommitted `一个事务的数据还为提交，就能被其他读事务看到`
* 读提交 read committed `一个事务提交后，才能被其他读事务看到`
* 可重复读 repeatable read `一个事务执行过程中，看到的数据，总是和他开始事务时读数据一致。当前事务，未提交事务，对其他事务也是不可见对`
* 串行化 serializable `串行执行，分别加读/写锁`
###  实现原理
> 创建一个视图

* 读未提交，没有视图，直接返回记录
* 读提交，是在`sql执行时`创建
*  可重复读，是在`事务启动时`创建
*  直接加锁的方式

#### MVCC 多版本并发控制
每条记录在更新的时候都会同时记录一条回滚日志

![](media/15715020143184.png)

#  深入浅出索引
> 提高查询效率

### 需要解决的问题
* 区间查询。 有序数组
* 等值查询。   hash表

#### 有序数组
* 等值查询，二分。LogN
* 区间查询
* 更新数据。成本太高
#### hash表
*  等值查询，O(1)
*   区间查询，全表扫描。O(N)
#### 二叉搜索树
* 等值查询。O（logN）。为了维护查询的时间复杂度，更新需要保持树的平衡，也是O（logN）
* 区间查询

## innodb的B+树索引
>  每一个索引，都是一颗B+树
* 主键索引
* 非主键索引
>  使用主键索引时，只查询主键索引一颗树。非主键索引，需要先查非主键的索引，拿到非主键索引上的主键ID，再去主键索引上查询。这个过程称为回表。

### 覆盖索引
>  减少索引的搜索

### 最左前缀原则
> 索引项是按照索引定义里面出现的顺序排序的
### 索引下推

# 全局锁和表锁

```
Flush tables with read lock（FTWRL）
``` 
* 整个库是只读

## 表锁
* 表锁
* 元数据锁（meta data lock ， MDL）

```
lock tables ... read/write
```
### MDL锁
* 不需要显示加锁，在访问一个表时会被自动加上
* 对表做增删改查时，加MDL读锁。当对表结构做更改时，加MDL写锁。

#### information_schema
 > innodb_trx 查询当前执行中的事务
 
```
ALTER TABLE tbl_name NOWAIT add column ...
ALTER TABLE tbl_name WAIT N add column ... 
```
# 行锁功过：怎么减少行锁对性能的影响？
行锁是在各个引擎中实现的。`innodb`支持行锁，`myisam`不支持行锁。
## 两阶段锁
> 前面有讲两阶段提交

>  在Innodb事务中，行锁是需要的时候才添加的，但并不是不需要了就立刻释放，而是要等到事务结束时才释放。这个就是两阶段锁协议。

## 死锁和死锁检测

|  事务A  |  事务B  |
| --- | --- |
|  begin；update t set k=k+1where id = 1;  |  begin;  |
|    |  update t set k = k+1 where id = 2;  |
|  update t set k=k+1 where id = 2;  |    |
|    |  update t set k=k+1 where id= 1;  |

 



