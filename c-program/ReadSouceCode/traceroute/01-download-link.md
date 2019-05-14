
[website](http://traceroute.sourceforge.net/)
[download](https://sourceforge.net/projects/traceroute/files/traceroute/)

### make 
```
$tar xzvf traceroute-2.1.0.tar.gz
$cd traceroute-2.1.0

$make
gcc -O2 -Wall  -D_GNU_SOURCE -c clif.c
rm -f libsupp.a
ar -rc libsupp.a clif.o
ranlib libsupp.a
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-icmp.c
gcc -O2 -Wall  -D_GNU_SOURCE -c time.c
gcc -O2 -Wall  -D_GNU_SOURCE -c module.c
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-raw.c
gcc -O2 -Wall  -D_GNU_SOURCE -c poll.c
gcc -O2 -Wall  -D_GNU_SOURCE -c csum.c
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-udp.c
gcc -O2 -Wall  -D_GNU_SOURCE -c extension.c
gcc -O2 -Wall  -D_GNU_SOURCE -c traceroute.c
gcc -O2 -Wall  -D_GNU_SOURCE -c as_lookups.c
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-tcp.c
gcc -O2 -Wall  -D_GNU_SOURCE -c random.c
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-tcpconn.c
gcc -O2 -Wall  -D_GNU_SOURCE -c mod-dccp.c
gcc -s -o traceroute mod-icmp.o time.o module.o mod-raw.o poll.o csum.o mod-udp.o extension.o traceroute.o as_lookups.o mod-tcp.o random.o mod-tcpconn.o mod-dccp.o  -lsupp -lm
```
### fun
```
$./traceroute/traceroute www.baidu.com

Usage:
  ./traceroute/traceroute [ -46dFITnreAUDV ] [ -f first_ttl ] [ -g gate,... ] [ -i device ] [ -m max_ttl ] [ -N squeries ] [ -p port ] [ -t tos ] [ -l flow_label ] [ -w MAX,HERE,NEAR ] [ -q nqueries ] [ -s src_addr ] [ -z sendwait ] [ --fwmark=num ] host [ packetlen ]
```

