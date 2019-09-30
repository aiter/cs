#define MAX_LINE 1024
#define FD_INIT_SIZE 128

char rot13_char(char c) {
	if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M'))
		return c + 13;
	else if (( c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z'))
		return c - 13;
	else 
		return c;
}

//数据缓冲区
struct Buffer {
	int connect_fd; //连接字
	char buffer[MAX_LINE]; //实际缓冲
	size_t writeIndex;
	size_t readIndex;
	int readable;
};

struct Buffer *alloc_buffer() {
	struct Buffer *buffer = malloc(sizeof(struct Buffer));
	if (!buffer)
		return NULL;
	buffer->connect_fd = 0;
	buffer->writeIndex = buffer->readIndex = buffer->readable = 0;
	return buffer;
}

void free_buffer(struct Buffer *buffer) {
	free(buffer);
}

int onSocketRead(int fd, struct Buffer *buffer) {
	char buf[1024];
	int i;
	ssize_t result;
	while (1) {
		result = recv(fd, buf, sizeof(buf), 0);
		if (result <= 0)
			break;

		for (i = 0; i < result; ++i) {
			if (buffer->writeIndex < sizeof(buffer->buffer))
				buffer->buffer[buffer->writeIndex++] = rot13_char(buf[i]);
			if (buf[i] == '\n') {
				buffer->readable = 1;
			}
		}
	}
}

if (result == 0) {
	return 1;
} else if (result < 0) {
	if (error == EAGAIN)
		return 0;
	return -1;
}
