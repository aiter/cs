### ThreadPoolExecutor
|  |coreSize | maxSize | BlockingQueue | 
| --- | --- | --- |---|
|newFixedThreadPool | n | n | LinkedBlockingQueue|
|newSingleThreadExecutor| 1 | 1 | LinkedBlockingQueue|
|newCachedThreadPool| 0 | Integer.MAX_VALUE | SynchronousQueue |
|newSingleThreadScheduledExecutor| n | Integer.MAX_VALUE | DelayedWorkQueue |

#### BlockingQueue
* LinkedBlockingQueue 无界队列(最大可以放Integer.MAX_VALUE) 
* SynchronousQueue 无空间队列
* DelayedWorkQueue 



