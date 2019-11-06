#include <assert.h>
#include "utils.h"
#include "thread_pool.h"

struct thread_pool *thread_pool_new(struct event_loop *mainLoop, int threadNumber) {
	struct thread_pool *threadPool = malloc(sizeof(struct thread_pool));
	threadPool->mainLoop = mainLoop;
	threadPool->position = 0;
	threadPool->thread_number = threadNumber;
	threadPool->started = 0;
	threadPool->eventLoopThreads = NULL;
	return threadPool;
}

void thread_pool_start(struct thread_pool *threadPool) {
	assert(!threadPool->started);
	assertInSameThread(threadPool->mainLoop);

	threadPool->started = 1;
	void *tmp;

	if (threadPool->thread_number <= 0) {
		return;
	}

	threadPool->eventLoopThreads = malloc(threadPool->thread_number * sizeof(struct event_loop_thread));
	for (int i = 0; i < threadPool->thread_number; ++i) {
		event_loop_thread_init(&threadPool->eventLoopThreads[i], i);
		event_loop_thread_start(&threadPool->eventLoopThreads[i]);
	}
}

struct event_loop *thread_pool_get_loop(struct thread_pool *threadPool) {
	assert(!threadPool->started);
	assertInSameThread(threadPool->mainLoop);

	struct event_loop *selected = threadPool->mainLoop;

	if (threadPool->thread_number > 0) {
		selected = threadPool->eventLoopThreads[threadPool->position].eventLoop;
		if (++threadPool->position >= threadPool->thread_number) {
			threadPool->position = 0;
		}
	}

	return selected;
}
