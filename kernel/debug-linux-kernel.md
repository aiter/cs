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

### 下载ubuntu iso文件
```
wget http://releases.ubuntu.com/18.04/ubuntu-18.04.3-live-server-amd64.iso
```

创建一个镜像
qemu-img create -f qcow2 ubuntu-18.04.3-live-server-amd64.img 10G

指定安装文件并安装系统
qemu-kvm -hda ubuntu-18.04.3-live-server-amd64.img -cdrom ubuntu-18.04.3-live-server-amd64.iso -boot d -m 2028 -vnc 0.0.0.0:4
> 将系统安装到上一步创建的镜像。指定vnc,可以从vnc viewer client链接到

安装完成后，退出cdrom,使用镜像启动



### 
virsh dumpxml ubuntutest|grep vnc

