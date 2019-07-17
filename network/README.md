### 《UNIX 网络编程》卷1:套接字联网API
> begin from 2019.7.15


迭代服务器(iterative server)
> 对每个客户迭代执行一次
并发服务器(concurrent server)
> * fork 函数，创建一个子进程
> * 线程代替fork
> * 预先fork一定数量的子进程
