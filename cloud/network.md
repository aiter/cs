### 负载均衡
SLB(Server Load Balancer)
CLB(Cloud Load Balancer)
原理基本都一样，四层+七层
* TCP/UDP 走四层(LVS/TGW),然后直接到后端服务器
* http/https 走四层(LVS/TGW)，然后到(Tengine/STGW),然后在到后端web服务器

https://help.aliyun.com/document_detail/27544.html
https://cloud.tencent.com/document/product/214/530
