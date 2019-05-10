### c源码
```
// function_example.c
#include <stdio.h>
int static add(int a, int b)
{
    return a+b;
}

int main()
{
    int x = 5;
    int y = 10;
    int u = add(x, y);
}
```
编译
```
gcc -g -c function_example.c
objdump -d -S function_example.o
```
汇编代码
```
function_example.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <add>:
// function_example.c
#include <stdio.h>
int static add(int a, int b)
{
   0:	55                   	push   %rbp
   1:	48 89 e5             	mov    %rsp,%rbp
   4:	89 7d fc             	mov    %edi,-0x4(%rbp)
   7:	89 75 f8             	mov    %esi,-0x8(%rbp)
    return a+b;
   a:	8b 45 f8             	mov    -0x8(%rbp),%eax
   d:	8b 55 fc             	mov    -0x4(%rbp),%edx
  10:	01 d0                	add    %edx,%eax
}
  12:	5d                   	pop    %rbp
  13:	c3                   	retq   

0000000000000014 <main>:

int main()
{
  14:	55                   	push   %rbp
  15:	48 89 e5             	mov    %rsp,%rbp
  18:	48 83 ec 10          	sub    $0x10,%rsp
    int x = 5;
  1c:	c7 45 fc 05 00 00 00 	movl   $0x5,-0x4(%rbp)
    int y = 10;
  23:	c7 45 f8 0a 00 00 00 	movl   $0xa,-0x8(%rbp)
    int u = add(x, y);
  2a:	8b 55 f8             	mov    -0x8(%rbp),%edx
  2d:	8b 45 fc             	mov    -0x4(%rbp),%eax
  30:	89 d6                	mov    %edx,%esi
  32:	89 c7                	mov    %eax,%edi
  34:	e8 c7 ff ff ff       	callq  0 <add>
  39:	89 45 f4             	mov    %eax,-0xc(%rbp)
}
  3c:	c9                   	leaveq 
  3d:	c3                   	retq
```
> 30:将edx 的值放入esi
> 32:将eax的值放入edi
> 34:调用add方法
> 4: 将edi的压入栈中
> 7: 将esi的压入栈中
