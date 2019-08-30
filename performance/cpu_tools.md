### vmstat

### pidstat(sysstat)
### mpstat(sysstat)

### /proc/interrupts

### top ps uptime

### perf
> perf top 
> perf report -g
> perf record

### pstree
> display a tree of processes

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
