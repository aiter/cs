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
* Exclusive:独占
* Share:共享
* tryAcquire(int)：独占方式。尝试获取资源，成功则返回true，失败则返回false。
* tryRelease(int)：独占方式。尝试释放资源，成功则返回true，失败则返回false。
* tryAcquireShared(int)：共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
* tryReleaseShared(int)：共享方式。尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false。
> ReentrantLock，state初始化为0，表示未锁定状态。A线程lock()时，会调用tryAcquire()独占该锁并将state+1。此后，其他线程再tryAcquire()时就会失败，直到A线程unlock()到state=0（即释放锁）为止，其它线程才有机会获取该锁。当然，释放锁之前，A线程自己是可以重复获取此锁的（state会累加），这就是可重入的概念。但要注意，获取多少次就要释放多么次，这样才能保证state是能回到零态的。
> CountDownLatch，任务分为N个子线程去执行，state也初始化为N（注意N要与线程个数一致）。这N个子线程是并行执行的，每个子线程执行完后countDown()一次，state会CAS减1。等到所有子线程都执行完后(即state=0)，会unpark()调用线程，然后调用线程就会从await()函数返回，继续后余动作。
