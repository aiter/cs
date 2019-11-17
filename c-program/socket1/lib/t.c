//#include "log.h"
#include    <stdio.h>

int main() {
	char name[13] = "StudyTonight";
	char fmt[10] = "[%s] %s\n";
	yolanda_log(1,name);
	error1(0,1,fmt,name);

//	err_dump(name,1);
}
