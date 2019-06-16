// 使用ipv4（AF_INET）还是ipv6(AF_INET6)
check_progname(const char *name)
//获取对应的module
ops = tr_get_module (const char *name)
//ops的init在注册时指定，必须icmp的
ops->init  
```c
static tr_module icmp_ops = { 
    .name = "icmp",
    .init = icmp_init, //ops->init 就是调用icmp_init函数
    .send_probe = icmp_send_probe,
    .recv_probe = icmp_recv_probe,
    .expire_probe = icmp_expire_probe,
    .options = icmp_options,
};
```
// init 中主要就是调用socket & connect server
// init 中会在client中将fd加入poll的
do_it()
