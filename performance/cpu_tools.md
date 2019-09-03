### vmstat

### pidstat(sysstat)
### mpstat(sysstat)

### dstat

### iostat

### ifstat

### /proc/interrupts

### top ps uptime

### perf
> perf top 
> perf report -g
> perf record

### strace
> 系统调用的跟踪的工具

### pstree
> display a tree of processes

### sar
> Collect, report, or save system activity information

## 压测工具
### stress
> 系统压力测试工具
> stress -i 1 --timeout 600
> stress -c 1 --timeout 600
> stress -m 1 --timeout 600
> stress -d 1 --timeout 600

### sysbench
> multi-threaded benchmark tool for database systems
> sysbench --threads=10 --time=300 threads run

### ab
> http 压测
> ab -c 10 -n 1000 http://xxx.xxx.com

### hping3
> send (almost) arbitrary TCP/IP packets to network hosts
