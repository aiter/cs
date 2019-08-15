### qemu

### libvirt

```shell
brctl addbr br0
ip link set br0 up
ifconfig br0 192.168.57.1/24

vi /etc/sysctl.conf
net.ipv4.ip_forward=1

iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
```
