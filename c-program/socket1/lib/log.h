#ifndef LOG_H
#define LOG_H

#include "stdarg.h" //ISO C Standard:  7.15  Variable arguments  <stdarg.h>

#define LOG_DEBUG_TYPE 0
#define LOG_INFO_TYPE   1
#define LOG_WARN_TYPE  2
#define LOG_ERR_TYPE   3

//void error1(int status, int err, char *fmt, ...);
void yolanda_log(int severity, const char *msg);
void yolanda_logx(int severity, const char *errstr, const char *fmt, va_list ap);
void yolanda_msgx(const char *fmt, ...);
void yolanda_debugx(const char *fmt, ...);

#define LOG_MSG(msg) yolanda_log(LOG_INFO_TYPE, msg)
#define LOG_ERR(msg) yolanda_log(LOG_ERR_TYPE, msg)

#endif
