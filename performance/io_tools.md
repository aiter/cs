* filetop
> 跟踪内核中文件的读写情况，并输出线程ID(TID)、读写大小、读写类型以及文件名称。

* opensnoop
> 动态跟踪内核中的open系统调用。

### io问题常用分析步骤
1. top
> 查看cpu的情况.特别注意观察wa(iowait占用cpu的比例)。同时观察进程使用cpu的情况
2. iostat -d -x 1
> 查看磁盘的读写情况
3. pidstat -d 1 
> 查看对应的程序和进程对磁盘的读写
4. strace -f -p XXX  XXX 是对应的pid
> 查看pid对应的系统调用。注意是子进程、子线程对fd的操作。
>
> pstree -t -a -p XXX     -t 显示线程；-a 显示命令行参数
5. lsof -p XXX XXX是对应的pid
> 查看pid对应的fd及打开的文件
