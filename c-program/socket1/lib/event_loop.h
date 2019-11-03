#ifndef EVENT_LOOP_H
#define EVENT_LOOP_H

#include <pthread.h>
#include "channel.h"
#include "event_dispatcher.h"
#include "common.h"

// poll
extern const struct event_dispatcher poll_dispatcher;
// epoll
extern const struct event_dispatcher epoll_dispatcher;

//channel Node
struct channel_element {
	int type; //1:add 2:delete
	struct channel *channel;
	struct channel_element *next;
};

//event_loop的主要struct
struct event_loop {
	int quit;
	//分发器，看具体的实现
	const struct event_dispatcher *eventDispatcher;

	void *event_dispatcher_data;
	struct channel_map *channelMap;

	int is_handle_pending;
	struct channel_element *pending_head;
	struct channel_element *pending_tail;

	pthread_t owner_thread_id;
	pthread_mutext_t mutex;
	pthread_cond_t cond;
	int socketPair[2];
	char *thread_name;
};

struct event_loop *event_loop_init();

struct event_loop *event_loop_init_with_name(char * thread_name);

int event_loop_run(struct event_loop *eventLoop);

void event_loop_wakeup(struct event_loop *eventLoop);

int event_loop_add_channel_event(struct event_loop *eventLoop, int fd, struct channel *channel1);

int event_loop_remove_channel_event(struct event_loop *eventLoop, int fd, struct channel *channel1);

int event_loop_update_channel_event(struct event_loop *eventLoop, int fd, struct channel *channel1);

int event_loop_handle_pending_add(struct event_loop *eventLoop, int fd, struct channel *channel1);

int event_loop_handle_pending_remove(struct event_loop *eventLoop, int fd, struct channel *channel1);

int event_loop_handle_pending_update(struct event_loop *eventLoop, int fd, struct channel *channel1);

int channel_event_activate(struct event_loop *eventLoop, int fd, int res);

#endif