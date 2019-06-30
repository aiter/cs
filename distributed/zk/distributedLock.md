两类
1. 保持独占
> 把znode看着一把锁，使用createznode的方式来实现。创建成功的client拥有锁，删除znode节点释放锁。

2. 控制时序
> znode存在，创建临时顺序编号目录节点，和选master一样，编号最小的获得锁，用完删除。
