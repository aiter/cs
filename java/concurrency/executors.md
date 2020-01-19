### ThreadPoolExecutor
|    |coreSize | maxSize | BlockingQueue | 
| --- | --- | --- |---|
|newFixedThreadPool | n | n | LinkedBlockingQueue|
|newSingleThreadExecutor| 1 | 1 | LinkedBlockingQueue|
|newCachedThreadPool| 0 | Integer.MAX_VALUE | SynchronousQueue |
|newSingleThreadScheduledExecutor| n | Integer.MAX_VALUE | DelayedWorkQueue |
|newWorkStealingPool(ForkJoinPool)|  | | |

#### BlockingQueue
* LinkedBlockingQueue 无界队列(最大可以放Integer.MAX_VALUE) 
* SynchronousQueue 无空间队列
* DelayedWorkQueue 

#### ExecutorService

```java
   // 接口
   public interface ExecutorService extend Executor

   public abstract class AbstractExecutorService implements ExecutorService

   //实例化的具体对象
   public class ThreadPoolExecutor extends AbstractExecutorService

   //Executors 静态工厂方法
   public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```



