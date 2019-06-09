### ipc 内存间通信
* 管道
* fifo
> 命名管道
> mkfifo fifoname
> pr1 < fifoname &
> pr2 < infile | tee fifoname | pr3
> //tee: Copy standard input to each FILE, and also to standard output.
> //mkfifo: Create **named pipes (FIFOs)** with the given NAMEs.
