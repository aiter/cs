#include "event_dispatcher.h"
#include "event_loop.h"
#include "log.h"

#define INIT_POLL_SIZE 1024

struct pollidx {
	int idxplus1;
};

struct poll_dispatcher_data {
	int event_count;
	int nfds;
	int realloc_copy;
	struct pollfd *event_set;
	struct pollfd *event_set_copy;
};

static void *poll_init(struct event_loop *);

static int poll_add(struct event_loop *, struct channel *);

static int poll_del(struct event_loop *, struct channel *);

static int poll_update(struct event_loop *, struct channel *);

static int poll_dispatch(struct event_loop *, struct timeval *);

static void *poll_clear(struct event_loop *);

const struct event_dispatcher poll_dispatcher = {
	"poll",
	poll_init,
	poll_add,
	poll_del,
	poll_update,
	poll_dispatch,
	poll_clear,
};

void *poll_init(struct event_loop *eventLoop) {
	struct poll_dispatcher_data *pollDispatcherData = malloc(sizeof(struct poll_dispatcher_data));

	pollDispatcherData->event_set = malloc(sizeof(struct pollfd) * INIT_POLL_SIZE);

	int i;
	for (i = 0; i < INIT_POLL_SIZE; i++)
		pollDispatcherData->event_set[i].fd = -1;

	pollDispatcherData->event_count = 0;
	pollDispatcherData->nfds= 0;
	pollDispatcherData->realloc_copy = 0;

	return pollDispatcherData;
}

int poll_add(struct event_loop *eventLoop, struct channel *channel) {
	struct poll_dispatcher_data *pollDispatcherData = (struct poll_dispatcher_data *) eventLoop->event_dispatcher_data;

	int fd = channel->fd;

	int events = 0;
	if (channel->events & EVENT_READ) {
		events = events | POLLRDNORM;
	}

	if (channel->events & EVENT_WRITE) {
		events = events | POLLWRNORM;
	}

	int i = 0;
	for (i = 0; i < INIT_POLL_SIZE; i++) {
		if (pollDispatcherData->event_set[i].fd < 0){
			pollDispatcherData->event_set[i].fd = fd;
			pollDispatcherData->event_set[i].events = events;
			break;
		}
	}

	yolanda_msgx("poll added channel fd==%d, %s", fd, eventLoop->thread_name);
	if (i > INIT_POLL_SIZE) {
		LOG_ERR("too many clients, just abort it");
	}

	return 0;
}
