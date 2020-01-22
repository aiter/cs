## Semaphore

### Sync(abstract) extends AbstractQueuedSynchronizer

#### NonfairSync extends Sync
#### FairSync extends Sync

* tryAcquireShared  NonfairSync & FairSync 实现这个方法
* doAcquireSharedInterruptibly
