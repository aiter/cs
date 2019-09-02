### 自旋锁
 为了避免线程切换，自己多占一会cpu，然后再看是否能获取到锁
### 锁粗化
  针对锁的访问
### 轻量级锁
  针对，重量级锁，使用CAS清除互斥量，和记录存在对象头中，在有竞争时当然也会升级为重量级锁
### 重量级锁

### 偏向锁
  针对轻量级锁、重量级锁，这个竞争条件，可能只有一个线程会使用"偏向"这一个线程，那么连轻量级中的CAS都可以不用，直接给这个线程“使用”共享变量
### 锁消除
  没有多线程竞争的情况，可以把锁直接清除掉

### synchronized
  * 实例方法上，使用synchronized，锁定的对象是实例
  * 静态方法上，使用synchronized，锁定的对象是类实例
  * 代码块上，使用synchronized，可以锁定一个特定的对象实例
```java
  public class TestSync implements Runnable {
	private TestSync instance = new TestSync();

	@Override
	public void run() {
		synchronized(instance) {
			....
		}
	}
}
```
