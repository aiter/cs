### traceroute支持的协议
* UDP
* ICMP
* TCP
* RAW
* ...

### 主要的struct
#### 协议的struct
```
struct tr_module_struct {
    struct tr_module_struct *next; //指向next
    const char *name; //default,udp,icmp
    int (*init) (const sockaddr_any *dest,
                unsigned int port_seq, size_t *packet_len);//初始化方法，每个module自己实现
    void (*send_probe) (probe *pb, int ttl);//发送探针
    void (*recv_probe) (int fd, int revents);//接收
    void (*expire_probe) (probe *pb);
    CLIF_option *options;   /*  per module options, if any   */
    int one_per_time;   /*  no simultaneous probes   */
    size_t header_len;  /*  additional header length (aka for udp)   */
};
typedef struct tr_module_struct tr_module; // typedef为tr_module,代码中基本使用tr_module
```
#### 探针的struct
```
struct probe_struct {
    ineet done;
    int final;
    sockaddr_any res;
    double send_time; 
    double recv_time;
    int recv_ttl;
    int sk;
    int seq;
    char *ext;
    char err_str[16];   /*  assume enough   */
};
typedef struct probe_struct probe;
``` 

### 在traceroute/traceroute.h中定义了一个宏
> 用于注册一个module 

```
#define TR_MODULE(MOD)  \
static void __init_ ## MOD (void) __attribute__ ((constructor));    \
static void __init_ ## MOD (void) { \
                \
    tr_register_module (&MOD);  \
}
```

#### 各协议实现的module里
> traceroute/mod-icmp.c
> traceroute/mod-udp.c
> ....

```
//各自的实现，注册进去
static tr_module icmp_ops = {
    .name = "icmp",
    .init = icmp_init,
    .send_probe = icmp_send_probe,
    .recv_probe = icmp_recv_probe,
    .expire_probe = icmp_expire_probe,
    .options = icmp_options,
};

TR_MODULE (icmp_ops);
```

#### 在来看看tr_register_module
```
tatic tr_module *base = NULL;
//注册调用，一个单向链表，后注册的插在列表的头部
void tr_register_module (tr_module *ops) {

    ops->next = base;
    base = ops;
}
//查找，从链表头，开始查找，名字相同就返回
const tr_module *tr_get_module (const char *name) {
    const tr_module *ops;

    if (!name)  return 0;

    for (ops = base; ops; ops = ops->next) {
        if (!strcasecmp (name, ops->name))
            return ops;
    }   

    return NULL;
}
```

### module注册顺序及name
顺序
```
icmp
icmp->|
raw
raw->icmp->|
default
default->raw->icmp->|
udp
udp->default->raw->icmp->|
udplite
udplite->udp->default->raw->icmp->|
tcp
tcp->udplite->udp->default->raw->icmp->|
tcpconn
tcpconn->tcp->udplite->udp->default->raw->icmp->|
dccp
dccp->tcpconn->tcp->udplite->udp->default->raw->icmp->|
```
mod-udp.c 注册了3个module，init方法不同
* default
* udp
* udplite
