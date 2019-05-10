
### 先来个例子
```
int main()
{
    int a = 0;
    int i;
    for (i = 0; i < 3; i++)
    {
        a += i;
    }
}
```
编译为汇编
```
 gcc -g -c loop_test.c
 objdump -d -S loop_test.o
```
汇编代码
```
loop_test.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <main>:
#include <time.h>
#include <stdlib.h>


int main()
{
   0:	55                   	push   %rbp
   1:	48 89 e5             	mov    %rsp,%rbp
   4:	48 83 ec 10          	sub    $0x10,%rsp
  srand(time(NULL));
   8:	bf 00 00 00 00       	mov    $0x0,%edi
   d:	e8 00 00 00 00       	callq  12 <main+0x12>
  12:	89 c7                	mov    %eax,%edi
  14:	e8 00 00 00 00       	callq  19 <main+0x19>
  int r = rand() % 2;
  19:	e8 00 00 00 00       	callq  1e <main+0x1e>
  1e:	99                   	cltd   
  1f:	c1 ea 1f             	shr    $0x1f,%edx
  22:	01 d0                	add    %edx,%eax
  24:	83 e0 01             	and    $0x1,%eax
  27:	29 d0                	sub    %edx,%eax
  29:	89 45 fc             	mov    %eax,-0x4(%rbp)
  int a = 10;
  2c:	c7 45 f8 0a 00 00 00 	movl   $0xa,-0x8(%rbp)
  if (r == 0)
  33:	83 7d fc 00          	cmpl   $0x0,-0x4(%rbp)
  37:	75 09                	jne    42 <main+0x42>
  {
    a = 1;
  39:	c7 45 f8 01 00 00 00 	movl   $0x1,-0x8(%rbp)
  40:	eb 07                	jmp    49 <main+0x49>
  } else {
    a = 2;
  42:	c7 45 f8 02 00 00 00 	movl   $0x2,-0x8(%rbp)
  } 
}
  49:	c9                   	leaveq 
  4a:	c3                   	retq
```

### 使用gdb看看寄存器
准备工作
```

```
寄存器
```
(gdb) info registers 
rax            0x40052b	4195627
rbx            0x0	0
rcx            0x400570	4195696
rdx            0x7fffffffe418	140737488348184
rsi            0x7fffffffe408	140737488348168
rdi            0x1	1
rbp            0x7fffffffe320	0x7fffffffe320
rsp            0x7fffffffe308	0x7fffffffe308
r8             0x7ffff7dd5e80	140737351868032
r9             0x0	0
r10            0x7fffffffe170	140737488347504
r11            0x7ffff7a3ba20	140737348090400
r12            0x400400	4195328
r13            0x7fffffffe400	140737488348160
r14            0x0	0
r15            0x0	0
rip            0x400533	0x400533 <main+8>
eflags         0x212	[ AF IF ]
cs             0x33	51
ss             0x2b	43
ds             0x0	0
es             0x0	0
fs             0x0	0
gs             0x0	0
```

### 寄存器
> 计算过程中暂存数据

| 寄存器 | 64位 | 32位 | 16位| 说明 |
| --- | --- | --- | --- | --- |
| 累加寄存器 | RAX | EAX | AX  | 通用寄存器|
| 基址寄存器 | RBX | EBX | BX  | 通用寄存器|
| 计数寄存器 | RCX | ECX | AX  | 通用寄存器|
| 数据寄存器 | RDX | EDX | DX  | 通用寄存器|
| 堆栈基指针 | RBP | EBP | BP  | base point 指针变址寄存器|
| 堆栈顶指针 | RSP | ESP | SP  | stack point 指针变址寄存器|
| 函数参数 | RSI | ESI | SI  | 指针变址寄存器|
| 函数参数 | RDI | EDI | DI  | 指针变址寄存器|
|  | r8 |  |  |  |
|  | r9 |  |  |  |
|  | r10 |  |  |  |
|  | r11 |  |  |  |
|  | r12 |  |  |  |
|  | r13 |  |  |  |
|  | r14 |  |  |  |
|  | r15 |  |  |  |

> IP寄存器/pc寄存器：指令指针寄存器(Instruction Pointer Register),指向代码段下一条指令的位置。

| 寄存器 | 64位 | 32位 | 16位| 说明 |
| --- | --- | --- | --- | --- |
| IP寄存器 | RIP | EIP | IP  | 指向代码段下一条指令|

>段寄存器。每个进程都分代码段和数据段，为了指向不同进程的地址空间。

| 寄存器 | 64位 | 32位 | 16位| 说明 |
| --- | --- | --- | --- | --- |
|Code Segment Register| CS | |CS|代码段寄存器，代码在内存中的位置|
| Data Segment Register | DS | |DS| 数据段寄存器，数据在内存中的位置 |
|Stack Register| SS | |SS|栈寄存器，后进先出的数据结构|
| | ES | |ES| |
|  | fs |  |  |  |
|  | gs |  |  |  |
|  | eflags |  |  | 计算过程中产生的标志位  |

如果运算中需要加载内存中的数据，需要通过DS找到内存中的数据，加载到通用寄存器中。CS、DS都存放这一个段的起始地址。代码的偏移量在IP寄存器中，数据段的偏移量会放在通用寄存器中。
