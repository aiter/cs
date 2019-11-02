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
