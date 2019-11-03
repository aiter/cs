#include <assert.h>
#include "event_loop.h"
#include "common.h"
#include "log.h"
#include "event_dispathcer.h"
#include "channel.h"
#include "utils.h"

int event_loop_handle_pengding_channel(struct event_loop *eventLoop) {
	//get the lock
	pthread_mutex_lock(&eventLoop->mutex);
	eventLoop->is_handle_pending = 1;

	struct channel_element *channelElement = eventLoop->pending_head;
	while (channelElement != NULL) {
		//save into event_map
		struct channel *channel = channelElement->channel;
		int fd = channel->fd;
		if (channelElement->type == 1) {
			event_loop_handle_pending_add(eventLoop, fd, channel);
		} else if (channelElement->type == 2) {
			event_loop_handle_pending_remove(eventLoop, fd, channel);
		} else if (channelElement->type == 3) {
			event_loop_handle_pending_update(eventLoop, fd, channel);
		}
		channelElement = channelElement->next;
	}

	eventLoop->pending_head = eventLoop->pending_tail = NULL;
	eventLoop->is_handle_pending = 0;

	//release the lock
	pthread_mutex_unlock(&eventLoop->mutex);

	return 0;
}

void event_loop_channel_buffer_nolock(struct event_loop *eventLoop, int fd, struct channel *channel1, int type) {
	//add channel into the pending list
	struct channel_element *channelElement = malloc(sizeof(struct channel_element));
	channelElement->channel = channel1;
	channelElement->type = type;
	channelElement->next = NULL;

	if (eventLoop->pending_head == NULL) {
		eventLoop->pending_head = eventLoop->pending_tail = channelElement;
	} else {
		eventLoop->pending_tail->next = channelElement;
		eventLoop->pending_tail = channelElement;
	}
}

void event_loop_wakeup(struct event_loop *eventLoop) {
	char one = 'a';
	ssize_t n = write(eventLoop->socketPair[0], &one, sizeof one);
	if (n != sizeof one) {
		LOG_ERR("wakeup event loop thread failed");
	}
}

int handleWakeup(void *data) {
	struct event_loop *eventLoop = (struct event_loop *) data;
	char one;
	ssize_t n = read(eventLoop->socketPair[1], &one, sizeof one);
	if (n != sizeof one) {
	}
	yolanda_msgx("wakeup, %s", eventLoop->thread_name);
}


/*
 * 初始化
 */
struct event_loop *event_loop_init() {
	return event_loop_init_with_name(NULL);
}

struct event_loop *event_loop_init_with_name(char *thread_name) {
	struct event_loop *eventLoop = malloc(sizeof(struct event_loop));
	pthread_mutxt_init(&eventLoop-mutex, NULL);
	pthread_cond_init(&eventLoop->cond, NULL);

	if (thread_name != NULL) {
		eventLoop->thread_name = thread_name;
	} else {
		eventLoop->thread_name = "main thread";
	}

	eventLoop->quit = 0;
	eventLoop->channelMap = malloc(sizeof(struct channel_map));
	map_init(eventLoop->channelMap);

#ifdef EPOLL_ENABLE	
	yolanda_msgx("set epoll as dispatcher, %s", eventLoop->thread_name);
	eventLoop->eventDispatcher = &epoll_dispatcher;
#else
	yolanda_msgx("set poll as dispatcher, %s", eventLoop->thread_name);
	eventLoop->eventDispatcher = &poll_dispatcher;
#endif
	eventLoop->event_dispatcher_data = eventLoop->eventDispatcher->init(eventLoop);

	eventLoop->owner_thread_id = pthread_self();
	if (socketpair(AF_UNIX, SOCK_STREAM, 0, eventLoop->socketPair) < 0) {
		LOG_ERR("socketpair set failed");
	}
	eventLoop->is_handle_pending = 0;
	eventLoop->pending_head = NULL;
	eventLoop->pending_tail = NULL;

	struct channel *channle = channel_new(eventLoop->socketPair[1],EVENT_READ, handleWakeup, NULL, eventLoop);
	event_loop_add_channel_event(eventLoop, eventLoop->socketPair[0], channel);

	return eventLoop;
}

int event_loop_run(struct event_loop *eventLoop) {
	assert(eventLoop != NULL);

	struct event_dispathcer *dispatcher = eventLoop->eventDispatcher;

	if (eventLoop->owner_thread_id != pthread_self()) {
		exit(1);
	}

	yolanda_msgx("event loop run, %s", eventLoop->thread_name);
	struct timeval timeval;
	timeval.tv_sec = 1;

	while (!eventLoop->quit) {
		// block here to wait I/O event, and get active channels
		dispatcher->dispatch(eventLoop, &timeval);

		//handle the pending channel
		event_loop_handle_pending_channel(eventLoop);
	}

	yolanda_msgx("event loop end, %s", eventLoop->thread_name);
	return 0;
}
