#include "tcp_connection.h"
#include "utils.h"

int handle_connection_closed(struct tcp_connection *tcpConnection) {
	struct event_loop *eventLoop = tcpConnection->eventLoop;	
	struct channel *channel = tcpConnection->channel;
	event_loop_remove_channel_event(eventLoop, channel->fd, channel);
	if (tcpConnection->connectionClosedCallBack != NULL) {
		tcpConnection->connectionClosedCallBack(tcpConnection);
	}
}

int handle_read(void *data) {
	struct tcp_connection *tcpConnection = (struct tcp_connection *) data;
	struct buffer *input_buffer = tcpConnection->input_buffer;
	struct channel *channel = tcpConnection->channel;

	if (buffer_socket_read(input_buffer, channel->fd) > 0) {
		if (tcpConnection->messageCallBack != NULL) {
			tcpConnection->messageCallBack(input_buffer, tcpConnection);
		}
	} else {
		handle_connection_closed(tcpConnection);
	}
}

//发送缓冲区可以往外写
//把channel 对应的output_buffer不断往外发送


