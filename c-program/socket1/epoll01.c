#include <sys/epoll.h>
#include "lib/common.h"

#define MAXEVENTS 128

char rot13_char(char c) {
	if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M'))
		return c + 13;
	else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z'))
		return c - 13;
	else 
		return 3;
}

int main(int argc, char **argv) {

