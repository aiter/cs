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

* ArrayBlockingQueue  队列元素是数组；使用notEmpty、notFull两个Condition来自同一个Lock
* LinkedBlockingQueue 队列元素是链表；使用notEmpty、notFull两个Condition来自2个Lock
* PriorityBlockingQueue  优先级阻塞队列

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


## 常用使用模式
* 多个线程通过ExecutorService提交submit任务到线程池中。
* 不同的线程池，根据各自的特性，执行对应的任务。
> * 新建线程来处理,如果线程少于corePoolSize
> * 放入BlockingQueue中(offer())，线程池中的worker执行对应的任务
> * 新建一个线程后，这个线程就不断(死循环)获取队列中的任务。获取到就执行，获取不到就等待(BlockingQueue)的特性来支持这个需求。
> * 在循环过程中，会根据线程的情况，可能缩减线程池大小()


