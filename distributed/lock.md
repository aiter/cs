### mysql

### zk

### redis
> setnx    SETNX lock.foo <current Unix time + lock timeout + 1>
> get
> getset  GETSET lock.foo <current Unix timestamp + lock timeout + 1>
> 
> unlock

> In order to make this locking algorithm more robust, a client holding a lock should always check the timeout didn't expire before unlocking the key with DEL because client failures can be complex, not just crashing but also blocking a lot of time against some operations and trying to issue DEL after a lot of time (when the LOCK is already held by another client).
[link](https://redis.io/commands/setnx)

一种实现方式
> 注意使用场景
1. 是否是同一线程，获取锁和释放锁
2. 是否需要重入
3. 锁资源的锁定时间长短

> 下面这种实现，https://www.w3cschool.cn/redis/redis-yj3f2p0c.html。理论上可能没问题，但是需要考虑场景的贴合度
### 加锁代码
```java
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
```
### 解锁代码
```java
public class RedisTool {
    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
```
