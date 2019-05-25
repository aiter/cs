
## lseek
```c
#include <unistd.h>

off_t lseek(int fd, off_t offset, int whence);
```

|whence|含义|
|---|---|
|SEEK_SET|距离文件开始处offset个字节|
|SEEK_CUR|距离当前处加offset个字节，offset可正可负|
|SEEK_END|文件长度(结束)加offset个字节，offset可正可负|

> 成功，就返回文件的偏移量
> 描述符是管道、FIFO、网络socket，不能lseek，返回-1

### 示例lseek1.c
```
#include "apue.h"

int main(void)
{
	if (lseek(STDIN_FILENO, 0, SEEK_CUR) == -1)
		printf("cannot seek\n");
	else
		printf("seek OK\n");
	exit(EXIT_SUCCESS);
}
```

```
$gcc lseek1.c
$./a.out < /etc/passwd
seek OK
$cat < /etc/passwd | ./a.out
cannot seek
```
