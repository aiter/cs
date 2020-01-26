## Semaphore

### Sync(abstract) extends AbstractQueuedSynchronizer

#### NonfairSync extends Sync
#### FairSync extends Sync

* tryAcquireShared  NonfairSync & FairSync 实现这个方法
* doAcquireSharedInterruptibly

## CountDownLatch

### Sync extends AbstractQueuedSynchronizer
> 主要就是实现 tryReleaseShared 
> countDown:sync.releaseShared(1);
> await:sync.acquireSharedInterruptibly(1);
> await(long,TimeUnit):sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));

## CyclicBarrier
```
final CyclicBarrier barrier;
Runnable barrierAction =
 new Runnable() { public void run() { mergeRows(...); }};
 barrier = new CyclicBarrier(N, barrierAction);

//每个线程完成任务后，调用barrier.await();
``` 

## AbstractQueuedSynchronizer
### AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer
```java
     * <pre>
     *      +------+  prev +-----+       +-----+
     * head |      | <---- |     | <---- |     |  tail
     *      +------+       +-----+       +-----+
     * </pre>
```
> rely on
 first-in-first-out (FIFO) wait queues 。队列
* Node:
* state:
* 从尾部原子插入node，从头部原子出队列一个node
