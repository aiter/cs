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

## 不使用
### c源码
```
// function_example0.c
#include <stdio.h>
int static add()
{
    return 4;
}

int main()
{
    int x = 5;
    int y = 10;
    int u = add();
}
```
编译
```
gcc -g -c function_example0.c
objdump -d -S function_example0.o
```
汇编
```
function_example0.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <add>:
// function_example0.c
#include <stdio.h>
int static add()
{
   0:	55                   	push   %rbp
   1:	48 89 e5             	mov    %rsp,%rbp
    return 4;
   4:	b8 04 00 00 00       	mov    $0x4,%eax
}
   9:	5d                   	pop    %rbp
   a:	c3                   	retq   

000000000000000b <main>:

int main()
{
   b:	55                   	push   %rbp
   c:	48 89 e5             	mov    %rsp,%rbp
   f:	48 83 ec 10          	sub    $0x10,%rsp
    int x = 5;
  13:	c7 45 fc 05 00 00 00 	movl   $0x5,-0x4(%rbp)
    int y = 10;
  1a:	c7 45 f8 0a 00 00 00 	movl   $0xa,-0x8(%rbp)
    int u = add();
  21:	b8 00 00 00 00       	mov    $0x0,%eax
  26:	e8 d5 ff ff ff       	callq  0 <add>
  2b:	89 45 f4             	mov    %eax,-0xc(%rbp)
}
  2e:	c9                   	leaveq 
  2f:	c3                   	retq
```

## 18个参数
### c 源码
```
// function_example18.c
#include <stdio.h>
int static add(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k,int l, int m,int n,int o,int p ,int q,int r)
{
    return a+b+c+d+e+f+g+h+i+j+k+l+m;
}

int main()
{
    int x = 5;
    int y = 10;
    int u = add(x, y, 1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16);
}
```
compile

```
gcc -g -c function_example18.c
objdump -d -S function_example18.o
```
asm
```
function_example18.o:     file format elf64-x86-64


Disassembly of section .text:

0000000000000000 <add>:
// function_example18.c
#include <stdio.h>
int static add(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int k,int l, int m,int n,int o,int p ,int q,int r)
{
   0:	55                   	push   %rbp
   1:	48 89 e5             	mov    %rsp,%rbp
   4:	89 7d fc             	mov    %edi,-0x4(%rbp)
   7:	89 75 f8             	mov    %esi,-0x8(%rbp)
   a:	89 55 f4             	mov    %edx,-0xc(%rbp)
   d:	89 4d f0             	mov    %ecx,-0x10(%rbp)
  10:	44 89 45 ec          	mov    %r8d,-0x14(%rbp)
  14:	44 89 4d e8          	mov    %r9d,-0x18(%rbp)
    return a+b+c+d+e+f+g+h+i+j+k+l+m;
  18:	8b 45 f8             	mov    -0x8(%rbp),%eax
  1b:	8b 55 fc             	mov    -0x4(%rbp),%edx
  1e:	01 c2                	add    %eax,%edx
  20:	8b 45 f4             	mov    -0xc(%rbp),%eax
  23:	01 c2                	add    %eax,%edx
  25:	8b 45 f0             	mov    -0x10(%rbp),%eax
  28:	01 c2                	add    %eax,%edx
  2a:	8b 45 ec             	mov    -0x14(%rbp),%eax
  2d:	01 c2                	add    %eax,%edx
  2f:	8b 45 e8             	mov    -0x18(%rbp),%eax
  32:	01 c2                	add    %eax,%edx
  34:	8b 45 10             	mov    0x10(%rbp),%eax
  37:	01 c2                	add    %eax,%edx
  39:	8b 45 18             	mov    0x18(%rbp),%eax
  3c:	01 c2                	add    %eax,%edx
  3e:	8b 45 20             	mov    0x20(%rbp),%eax
  41:	01 c2                	add    %eax,%edx
  43:	8b 45 28             	mov    0x28(%rbp),%eax
  46:	01 c2                	add    %eax,%edx
  48:	8b 45 30             	mov    0x30(%rbp),%eax
  4b:	01 c2                	add    %eax,%edx
  4d:	8b 45 38             	mov    0x38(%rbp),%eax
  50:	01 c2                	add    %eax,%edx
  52:	8b 45 40             	mov    0x40(%rbp),%eax
  55:	01 d0                	add    %edx,%eax
}
  57:	5d                   	pop    %rbp
  58:	c3                   	retq   

0000000000000059 <main>:

int main()
{
  59:	55                   	push   %rbp
  5a:	48 89 e5             	mov    %rsp,%rbp
  5d:	48 83 ec 70          	sub    $0x70,%rsp
    int x = 5;
  61:	c7 45 fc 05 00 00 00 	movl   $0x5,-0x4(%rbp)
    int y = 10;
  68:	c7 45 f8 0a 00 00 00 	movl   $0xa,-0x8(%rbp)
    int u = add(x, y, 1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16);
  6f:	8b 75 f8             	mov    -0x8(%rbp),%esi
  72:	8b 45 fc             	mov    -0x4(%rbp),%eax
  75:	c7 44 24 58 10 00 00 	movl   $0x10,0x58(%rsp)
  7c:	00 
  7d:	c7 44 24 50 0f 00 00 	movl   $0xf,0x50(%rsp)
  84:	00 
  85:	c7 44 24 48 0e 00 00 	movl   $0xe,0x48(%rsp)
  8c:	00 
  8d:	c7 44 24 40 0d 00 00 	movl   $0xd,0x40(%rsp)
  94:	00 
  95:	c7 44 24 38 0c 00 00 	movl   $0xc,0x38(%rsp)
  9c:	00 
  9d:	c7 44 24 30 0b 00 00 	movl   $0xb,0x30(%rsp)
  a4:	00 
  a5:	c7 44 24 28 0a 00 00 	movl   $0xa,0x28(%rsp)
  ac:	00 
  ad:	c7 44 24 20 09 00 00 	movl   $0x9,0x20(%rsp)
  b4:	00 
  b5:	c7 44 24 18 08 00 00 	movl   $0x8,0x18(%rsp)
  bc:	00 
  bd:	c7 44 24 10 07 00 00 	movl   $0x7,0x10(%rsp)
  c4:	00 
  c5:	c7 44 24 08 06 00 00 	movl   $0x6,0x8(%rsp)
  cc:	00 
  cd:	c7 04 24 05 00 00 00 	movl   $0x5,(%rsp)
  d4:	41 b9 04 00 00 00    	mov    $0x4,%r9d
  da:	41 b8 03 00 00 00    	mov    $0x3,%r8d
  e0:	b9 02 00 00 00       	mov    $0x2,%ecx
  e5:	ba 01 00 00 00       	mov    $0x1,%edx
  ea:	89 c7                	mov    %eax,%edi
  ec:	e8 0f ff ff ff       	callq  0 <add>
  f1:	89 45 f4             	mov    %eax,-0xc(%rbp)
}
  f4:	c9                   	leaveq 
  f5:	c3                   	retq
```
