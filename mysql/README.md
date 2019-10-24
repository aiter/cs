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

* 一种策略，直接进入等待，直到超时。`innodb_wait_lock_timeout`
* 另一种策略，发起死锁检测，发现后，让其中的一条事务回滚，另外的事务可以继续。`innodb_deadlock_detect`设置为`on`

# 事务到底是隔离的还是不隔离的
**begin/start transaction 命令并不是一个事务的起点，在执行他们之后第一个操作innodb表的语句，才是事务真正启动**，如果项马上启动一个事务，使用`start transaction with consistent snapshot`

### 快照是怎么实现的？
每个事务有一个唯一的事务ID，transaction id。

![](media/15716365948975.png)

#### 实现上
InnoDB为每个事务构造了一个数组，用来保存事务的启动瞬间，当前正在“活跃”的所有事务ID，
* 数组里最小ID，记录为低水位值。
* 当前系统已经创建过的最大事务ID+1，记录为高水位值
* 这个数组和高水位，就组成了当前事务的一致性视图（read-view）

# 唯一索引和普通索引
### 查询
* 唯一索引，直接查找B+树，找到就返回
* 普通索引，查找B+树，找到了，还需要找前后满足的索引记录（但是都是在内存的一页中查询）
### 更新
*  唯一索引，需要先查询，检查唯一性，再更新
*  普通索引，可以直接更新到change buffer，语句就结束了

### change buffer 使用场景
*  写多读少，写完后不会很快被读到
*  账单类，日志类

# count(*)很慢
### 实现方式
> 不用的引擎，不同的实现方式

* MyISAM 把总行数存放在磁盘上，返回效率很高（没有where过滤条件的）
* InnoDB，读取每一行，累计计数

show table status的记录行数是不准确的。
innodb，统计总行数，但是会选择索引小的来统计
### 解决办法
* 使用redis等缓存系统来记录。有一定的问题
* 使用mysql的另一张表来记录，事务来保证两张表的原子性

## 不同count方法的性能问题
>  结论：尽量使用count(*)因为做过单独的优化。count(字段)<count(主键id)<count(1)约=count(*)

* count 字段，引擎返回字段给server层，server判断不是null的，就累计+1
* count 主键ID，需要引擎返回主键ID给server层，累计+1
* count 1，不需要引擎返回字段给server层
* count * ，专门的优化
 



