#include "apue.h"
#include <errno.h>
#include <stdarg.h>
#include <fcntl.h>

static void err_doit(int, int, const char *, va_list);

void set_fl(int fd, int flags)
{
	int val;
	if ((val = fcntl(fd, F_GETFL, 0)) < 0)
		err_sys("fcntl F_GTFL error");

	val |= flags;

	if(fcntl(fd, F_SETFL, val) < 0)
		err_sys("fcntl F_SETFL error");
}

void clr_fl(int fd, int flags)
{
	int val;
	if ((val = fcntl(fd, F_GETFL, 0)) < 0)
		err_sys("fcntl F_GTFL error");

	//TODO
	val = val & ~flags;

	if(fcntl(fd, F_SETFL, val) < 0)
		err_sys("fcntl F_SETFL error");
}

void err_ret(const char *fmt, ...) {
	va_list ap;
	va_start(ap, fmt);
	err_doit(1, errno, fmt, ap);
	va_end(ap);
}

void err_sys(const char *fmt, ...) {
	va_list ap;
	va_start(ap, fmt);
	err_doit(1, errno, fmt, ap);
	va_end(ap);
	exit(1);
}

static void err_doit(int errnoflag, int error, const char *fmt, va_list ap)
{
	char buf[MAXLINE];

 	vsnprintf(buf, MAXLINE - 1, fmt, ap);
	if(errnoflag)
		snprintf(buf + strlen(buf), MAXLINE - strlen(buf) - 1, ": %s", strerror(error));
	strcat(buf, "\n");
	fflush(stdout);
	fputs(buf, stderr);
	fflush(NULL);
}

