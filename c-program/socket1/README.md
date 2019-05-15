### socket 创建
socket系统调用创建一个套接字并返回一个描述符，该描述符可以用来访问该套接字
```
#include <sys/types.h>
#include <sys/socket.h>

int socket(int domain, int type, int protocol);
```
#### domain 执行协议族
|domain|说明|
|---|---|
|AF_UNIX|UNIX域协议（文件系统套接字）|
|AF_INET|ARPA因特网协议（UNIX网络套接字）|
|AF_ISO||
|AF_NS||
|AF_IPX||
|AF_APPLETALK||
> AF_UNIX & AF_INET最常用
#### type 通信类型
|type|说明|
|---|---|
|SOCK_STREAM|对于AF_INET来说，默认是TCP链接|
|SOCK_DGRAM|数据报服务，对于AF_INET来说，UDP|
|SOCK_RAW||

#### protocal 指定使用的协议

#### 实例1，traceroute中的icmp链接
```
protocol = (af == AF_INET) ? IPPROTO_ICMP : IPPROTO_ICMPV6;

if (!raw) {
    //domain:AF_INET, type:SOCK_DGRAM
    icmp_sk = socket (af, SOCK_DGRAM, protocol);
    if (icmp_sk < 0 && dgram)
        error ("socket");
}   

if (!dgram) {
    //domain:AF_INET, type:SOCK_RAW
    int raw_sk = socket (af, SOCK_RAW, protocol);
    if (raw_sk < 0) {
```
