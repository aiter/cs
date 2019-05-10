### start 
```
#include <stdio.h>

int add_range(int low, int high)
{
	int i, sum;
	for (i = low; i <= high; i++)
		sum = sum + i;
	return sum;
}

int main(void)
{
	int result[100];
	result[0] = add_range(1, 10);
	result[1] = add_range(1, 100);
	printf("result[0]=%d\nresult[1]=%d\n", result[0], result[1]);
	return 0;
}
```
compile
```
 gcc -g main.c -o main
 start
```

* list->l.  l function。
* next->n 执行下一条。
* step->s 进入函数
* backtrace-bt。查看函数调用的栈帧
* info-i 查看局部变量的值。i locals。 i breakpoints 设置的所有断点
* frame-f  查看x号栈帧的局部变量。f 1，然后 i locals
* print-p。p var。打印变量的值
* finish 一直运行到从当前函数返回为止
* set var sum=0。修改变量值，也可以使用print sum=0
* display sum 每次停下来，都显示sum的值。undisplay 取消跟踪。sum的编号是1，使用undisplay 1 来取消
* break-b。b 9 在第9行设置一个断点。break后面可以是行数，也可以是函数名
* delete breakpoints 2 删除指定编号的断点。结合 info breakpoints使用。 delete breakpoints 删除所有断点
* continve-c。连续运行而非单步，直接到达断点
* run-r。重新从程序开头连续运行
* enable 断点号。 启用断点
* x/7b input。 x命令打印指定存储单元的内容。7b是打印格式，b表示每个字节一组，7表示打印7组
watch。设置观察点。watch input[5].  i watchpoint
