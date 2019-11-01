#ifndef EVENT_DISPATCHER_H
#define EVENT_DISPATCHER_H

#include "channel.h"

/** 抽象的event_dispatcher 结构体， 对应的实现如select,poll,epoll等I/O复用 */
struct event_dispatcher {
	//对应的实现，如"poll/epoll/select"
	const char *name;

	// 初始化，返回 void * 
	void *(*init)(struct event_loop *eventLoop);

	// 添加一个channel，返回 int
	int (*add)(struct event_loop *eventLoop, struct channel *channel);

	int (*del)(struct event_loop *eventLoop, struct channel *channel);
	
	int (*update)(struct event_loop *eventLoop, struct channel *channel);

	int (*dispatch)(struct event_loop *eventLoop, struct timeval *);

	void (*clear)(struct event_loop *eventLoop);

};
#endif

