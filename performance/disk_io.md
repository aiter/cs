IOPS和数据吞吐量适用于不同的场合：
* 读取10000个1KB文件，用时10秒  Throught(吞吐量)=1MB/s ，IOPS=1000  追求IOPS
* 读取1个10MB文件，用时0.2秒  Throught(吞吐量)=50MB/s, IOPS=5  追求吞吐量
### IOPS
> Input/Output Per Second

### 吞吐量
> 单位时间内可以成功传输的数据数量。单位时间，一般也是秒(s)

### 测试工具：fio

### 性能数据
* 测试数据大小，一般使用4K
* 队列深度
* 顺序读/写，随机读/写

* 顺序读
SSD其速度可以达到404M/S，IOPS达到103K/S
SATA其速度可以达到124M/S，IOPS达到31K/S
* 顺序写
SSD其速度可以达到592M/S，IOPS达到152K/S
SATA其速度可以达到118M/S，IOPS达到31K/S

* 随机读
SSD其速度可以达到505M/S，IOPS达到129K/S
SATA其速度可以达到466K/S，IOPS达到114/S

* 随机写
SSD其速度可以达到549M/S，IOPS达到140K/S
SATA其速度可以达到548K/S，IOPS达到134/S
