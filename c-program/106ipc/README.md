### ipc 内存间通信
* 管道
* fifo
> 命名管道
>
> mkfifo fifoname
>
> pr1 < fifoname &
>
> pr2 < infile | tee fifoname | pr3
>
> //tee: Copy standard input to each FILE, and also to standard output.
>
> //mkfifo: Create **named pipes (FIFOs)** with the given NAMEs.
* 消息队列
> 避免使用
* 信号量
> 同步原语，不是IPC
>
> 还有POSIX的信号量
* 共享存储
> 对比mmap(需要对应的文件)
