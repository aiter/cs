#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
//#include <sys/un.h>
#include <netinet/in.h>
#include <sys/time.h>
#include <sys/ioctl.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
	int server_sockfd, client_sockfd;
	int server_len, client_len;
	//struct sockaddr_un server_address;
	//struct sockaddr_un client_address;
	struct sockaddr_in server_address;
	struct sockaddr_in client_address;
	int result;
	fd_set readfds, testfds;
	//服务器套接字
	server_sockfd = socket(AF_INET, SOCK_STREAM, 0);
	//命名套接字：
	server_address.sin_family = AF_INET;
	server_address.sin_addr.s_addr = inet_addr("127.0.0.1");
	server_address.sin_port = htons(9734);
	server_len = sizeof(server_address);
	//bind
	bind(server_sockfd, (struct sockaddr *)&server_address, server_len);
	//创建一个连接队列，开始等待客户进行连接：
	listen(server_sockfd, 5);
	FD_ZERO(&readfds);
	//将服务器套接字加入SET
	FD_SET(server_sockfd, &readfds);
	while(1) {
		char ch;
		int fd;
		int nread;
		testfds = readfds;
		printf("server waiting\n");
		//select ,第一次，肯定只有server_sockfd一个fd
		result = select(FD_SETSIZE, &testfds, (fd_set *)0, (fd_set *)0, (struct timeval *) 0);
		if(result < 1) {
			perror("server5");
			exit(1);
		}
		//select到可读、可写的fd，就遍历SET
		// epoll最主要的区别在这里？select需要从0遍历所有的fd，然后检查是否属于SET中的
		for(fd = 0; fd < FD_SETSIZE; fd++) {
			if(FD_ISSET(fd, &testfds)) { 
				//如果是server_sockfd上的，那么是新的链接
				if(fd == server_sockfd) {
					client_len = sizeof(client_address);
					client_sockfd = accept(server_sockfd, (struct sockaddr *)&client_address, &client_len);
					//添加新的fd，“下次”select就可能有这个fd了
					FD_SET(client_sockfd, &readfds);
					printf("adding client on fd %d\n", client_sockfd);
				}else {
					ioctl(fd, FIONREAD, &nread);
					//close的动作
					if(nread ==0) {
						close(fd);
						FD_CLR(fd, &readfds);
						printf("removing client on fd %d\n", fd);
					}else {
						read(fd, &ch, 1);
						sleep(5);
						printf("serving client on fd %d\n", fd);
						ch++;
						write(fd, &ch, 1);
					}
				}
			}
		}
	}
}
