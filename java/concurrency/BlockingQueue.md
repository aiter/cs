##  BlockingQueue
### 操作主要分为几种类型
* 抛异常
* 返回特殊的值(null or false)
* 锁住当前线程，直到操作成功
* 锁住当前线程一定的时间，直到超时

* 不接受null
* 生产-消费者队列，也支持集合(Collection)操作，所以支持remove这样的操作，但是效率可能不高。
* 线程安全

### ArrayBlockingQueue

### LinkedBlockingQueue

### DelayQueue

### 20.2.10
