### netfilter钩子（hooks）
```
         |
         | Incoming
         ↓
+-------------------+
| NF_IP_PRE_ROUTING |
+-------------------+
         |
         |
         ↓
+------------------+
|                  |         +----------------+
| routing decision |-------->| NF_IP_LOCAL_IN |
|                  |         +----------------+
+------------------+                 |
         |                           |
         |                           ↓
         |                  +-----------------+
         |                  | local processes |
         |                  +-----------------+
         |                           |
         |                           |
         ↓                           ↓
 +---------------+          +-----------------+
 | NF_IP_FORWARD |          | NF_IP_LOCAL_OUT |
 +---------------+          +-----------------+
         |                           |
         |                           |
         ↓                           |
+------------------+                 |
|                  |                 |
| routing decision |<----------------+
|                  |
+------------------+
         |
         |
         ↓
+--------------------+
| NF_IP_POST_ROUTING |
+--------------------+
         |
         | Outgoing
         ↓
```
### tables
* filter
* nat
* mangle 
> ip header;TTL;
* raw
* security

### chains 
* PREROUTING
* INPUT
* FORWARD
* OUTPUT
* POSTROUTING

```
                                    |
                                    | Incoming             ++---------------------++
                                    ↓                      || raw                 ||
                           +-------------------+           || connection tracking ||
                           | NF_IP_PRE_ROUTING |= = = = = =|| mangle              ||
                           +-------------------+           || nat (DNAT)          ||
                                    |                      ++---------------------++
                                    |
                                    ↓                                                ++------------++
                           +------------------+                                      || mangle     ||
                           |                  |         +----------------+           || filter     ||
                           | routing decision |-------->| NF_IP_LOCAL_IN |= = = = = =|| security   ||
                           |                  |         +----------------+           || nat (SNAT) ||
                           +------------------+                 |                    ++------------++
                                    |                           |
                                    |                           ↓
                                    |                  +-----------------+
                                    |                  | local processes |
                                    |                  +-----------------+
                                    |                           |
                                    |                           |                    ++---------------------++
 ++------------++                   ↓                           ↓                    || raw                 ||
 || mangle     ||           +---------------+          +-----------------+           || connection tracking ||
 || filter     ||= = = = = =| NF_IP_FORWARD |          | NF_IP_LOCAL_OUT |= = = = = =|| mangle              ||
 || security   ||           +---------------+          +-----------------+           || nat (DNAT)          ||
 ++------------++                   |                           |                    || filter              ||
                                    |                           |                    || security            ||
                                    ↓                           |                    ++---------------------++
                           +------------------+                 |
                           |                  |                 |
                           | routing decision |<----------------+
                           |                  |
                           +------------------+
                                    |
                                    |
                                    ↓
                           +--------------------+           ++------------++
                           | NF_IP_POST_ROUTING |= = = = = =|| mangle     ||
                           +--------------------+           || nat (SNAT) ||
                                    |                       ++------------++
                                    | Outgoing
                                    ↓
```

[netfilter/iptables简介](https://segmentfault.com/a/1190000009043962)
[iptables-tutorial](https://www.frozentux.net/iptables-tutorial/iptables-tutorial.html)
[a-deep-dive-into-iptables-and-netfilter-architecture](https://www.digitalocean.com/community/tutorials/a-deep-dive-into-iptables-and-netfilter-architecture)



