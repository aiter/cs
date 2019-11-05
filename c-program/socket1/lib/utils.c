#include "utils.h"
#include "log.h"

void assertInSameThread(struct event_loop *eventLoop) {
	if (eventLoop->owner_thread_id != pthread_self()) {
		LOG_ERR("not in the same thread");
		exit(-1);
	}
}

/**
 * 是否是同一个线程
 */
int isInSameThread(struct event_loop *eventLoop){
	return eventLoop->owner_thread_id == pthread_self();
}
