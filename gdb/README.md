编译
```
$ gcc -g main.c -o main
$ gdb main 
GNU gdb (GDB) Red Hat Enterprise Linux 7.6.1-80.el7
Copyright (C) 2013 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.  Type "show copying"
and "show warranty" for details.
This GDB was configured as "x86_64-redhat-linux-gnu".
For bug reporting instructions, please see:
<http://www.gnu.org/software/gdb/bugs/>...
Reading symbols from /home/liuyunjian/asm/a.out...done.
(gdb) 
```
start
```
(gdb)start
```
* start
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
* watch。设置观察点。watch input[5].  i watchpoint
