### qemu

### libvirt

```shell
brctl show
brctl addbr br0
ip link set br0 up
ifconfig br0 192.168.57.1/24

vi /etc/sysctl.conf
net.ipv4.ip_forward=1

iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
```

### 下载ubuntu iso安装文件
```
wget http://releases.ubuntu.com/18.04/ubuntu-18.04.3-live-server-amd64.iso
```

创建一个镜像
qemu-img create -f qcow2 ubuntu-18.04.3-live-server-amd64.img 10G

指定安装文件并安装系统
qemu-kvm -hda ubuntu-18.04.3-live-server-amd64.img -cdrom ubuntu-18.04.3-live-server-amd64.iso -boot d -m 2028 -vnc 0.0.0.0:4
> 将系统安装到上一步创建的镜像。指定vnc,可以从vnc viewer client链接到

安装完成后，退出cdrom,使用镜像启动

### 下载centos的镜像文件，已安装好的镜像
https://cloud.centos.org/centos/7/images/

### 
virsh dumpxml ubuntutest|grep vnc

### 修改网络
> /etc/netplan/50-cloud-init.yaml
```shell
network:
    ethernets:
        ens3:
                addresses: [192.168.57.100/24]
                gateway4: 192.168.57.1
                dhcp4: no
                nameservers:
                        addresses: [8.8.8.8,114.114.114.114]
                optional: true
    version: 2

```
> netplan apply

### 下载kernel
> ubuntu
apt-get install linux-source-4.15.0
> /usr/src
tar vjxkf linux-source-4.15.0.tar.bz2

### 内核编译
apt-get install libncurses5-dev libssl-dev bison flex libelf-dev gcc make openssl libc6-dev
> 编译选项
make menuconfig
> 编译
nohup make -j8 > make1.log 2>&1 &
> make相当于分别执行#make bzImage 和#make modules，由于是本机替换内核，所以无需分开执行。
nohup make modules_install > make2.log 2>&1 &
> 模块安装
nohup make install > make3.log 2>&1 &
> 内核安装
> 现在检查一下/boot目录是否生成了initrd镜像文件，一般通过上述步骤就会在/boot目录下生成满足内核启动条件的vmlinuz、initrd.img（当然/boot下附带还有System.map、config），如果/boot下没有initrd.img，则需要执行cd /boot 后通过mkinitrd -oinitrd.img- 生成initrd.img

```
make : compiles and links the kernel image. ...
make install : installs your built kernel to /vmlinuz .
make modules_install : installs your kernel modules to /lib/modules or /lib/modules/<version> .
```
