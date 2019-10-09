#include "lib/common.h"

#define THREAD_NUMBER 	4
#define BLOCK_QUEUE_SIZE	100

extern void loop_echo(int);

typedef struct {
	pthread_t thread_tid;
	long thread_count;
} Thread;

Thread *thread_array;

typedef struct {
	int number;
	int *fd;
	int front;
	int rear;
	pthread_mutex_t mutex;
	pthread_cond_t cond;
} block_queue;

void block_queue_init(block_queue *blockQueue, int number) {
	blockQueue->number = number;
	blockQueue->fd = calloc(number, sizeof(int));
	blockQueue->front = blockQueue->rear = 0;
	pthread_mutex_init(&blockQueue->mutex, NULL);
	pthread_cond_init(&blockQueue->cond, NULL);
}
