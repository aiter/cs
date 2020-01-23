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
