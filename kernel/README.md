## 源文件

### download 
> [https://www.kernel.org/](https://www.kernel.org/)

### 安装情况
> /root/  启动目录，包括
> * grub
> * 内核启动文件
> 
> /usr/include/ //头文件 内核源码：make headers_install
> 
> /usr/src/kernels/XXX   //内核源代码路径

### 源码包说明
|目录|用途|
|---|---|
|include|内核头文件|
|kernel|内核核心代码|
|mm|内存管理子系统|
|fs|VFS子系统|
|net|网络子系统(不包括网络设备驱动)|
|ipc|进程间通信子系统|
|arch|体系结构相关的代码，如x86、arm等|
|init|启动初始化相关|
|block|块设备相关|
|sound|音频相关的驱动及子系统|
|drivers|设备驱动，占内核代码的很高比例|
|lib|在内核中使用的库函数，如CRC、FIFO、list、MD5等|
|crypto|加密、解密相关库函数|
|security|安全相关|
|virt|提供虚拟机技术（KVM等）的支持|
|usr|用于生成initramfs的代码|
|firmware| 保存用于驱动第三方设备的固件|
|samples|一些示例代码|
|tools|一些常用工具，如性能剖析、自测试等|
|Kconfig, Kbuild, Makefile, scripts|用于内核编译的配置文件、脚本等|
|COPYING|版权声明|
|MAINTAINERS|维护者名单|
|CREDITS|Linux主要的贡献者名单|
|REPORTING-BUGS |Bug上报的指南|
