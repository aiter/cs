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

### inet-socket client (htons host to network short)
```
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
	int sockfd;
	int len;
	//struct sockaddr_un address;
	struct sockaddr_in address;
	int result;
	char ch = 'A';

	//为客户创建一个套接字
	//sockfd = socket(AF_UNIX, SOCK_STREAM, 0);
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	//根据服务器的情况给套接字命名
	//address.sun_family = AF_UNIX;
	address.sin_family = AF_INET;
	//strcpy(address.sun_path, "server_socket");
	address.sin_addr.s_addr = inet_addr("127.0.0.1");
	address.sin_port = htons(9734);
	len = sizeof(address);
	//将我们的套接字链接到服务器的套接字：
	result = connect(sockfd, (struct sockaddr *)&address, len);

	if(result == -1) {
		perror("oops:client2");
		exit(1);
	}
	//可以通过sockfd进行读写操作了
	write(sockfd, &ch, 1);
	read(sockfd, &ch, 1);
	printf("char from server = %c\n", ch);
	close(sockfd);
	exit(0);
}
```

### inet-socket server
```
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
//#include <sys/un.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
	int server_sockfd, client_sockfd;
	int server_len, client_len;
	//struct sockaddr_un server_address;
	//struct sockaddr_un client_address;
	struct sockaddr_in server_address;
	struct sockaddr_in client_address;
	//删除以前的套接字，为服务器创建一个未命名的套接字
	//unlink("server_socket");
	server_sockfd = socket(AF_INET, SOCK_STREAM, 0);
	//命名套接字：
	server_address.sin_family = AF_INET;
	server_address.sin_addr.s_addr = inet_addr("127.0.0.1");
	server_address.sin_port = htons(9734);
	//strcpy(server_address.sun_path, "server_socket");
	server_len = sizeof(server_address);
	bind(server_sockfd, (struct sockaddr *)&server_address, server_len);
	//创建一个连接队列，开始等待客户进行连接：
	listen(server_sockfd, 5);
	while(1) {
		char ch;
		printf("server waiting\n");
		//接受一个连接
		client_len = sizeof(client_address);
		client_sockfd = accept(server_sockfd, (struct sockaddr *)&client_address, &client_len);
		//对client_sockfd套接字上的客户进行读写操作
		read(client_sockfd, &ch, 1);
		ch++;
		write(client_sockfd, &ch, 1);
		close(client_sockfd);
	}
}
```
