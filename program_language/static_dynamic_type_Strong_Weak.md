### 动态类型 & 静态类型
静态类型
> 运行之前，就检查类型，比如在java/c++/c/golang,在编译阶段就检查类型。

动态类型
> 运行期间，判断类型

区别
> 变量类型是否在"编译"时确定
```
       +----------- Translation -------------------->--+
       |                                               |
+--------------+                              +--------------+
| Source Code  |                              | machine Code |
+--------------+                              +--------------+
编译型 +                          Compiled                          Run-Time
解释型                                                          Interpreted
```

### 强类型 & 弱类型
强类型
> 一个变量指定了类型，如果不强制转换，一直就是这个类型
> c/c++/c#/java/go

弱类型
> php/Ruby/JavaScript 

### examples
> 如下的golang，编译不通
```golang
package main

import "fmt"

func foo(a int) {
    if a > 0 {
        fmt.Println("hi")
    } else {
        fmt.Println("3" + 5)
    }
}

func main() {
    fmt.Println("vim-go")
    foo(2)
}
```

> 如下的python，在调用foo(-1),走else逻辑时，才出错
```python
def foo(a):
    if a > 0:
            print "hi"
    else:
            print "3" + 5

foo(2)
hi
foo(-1)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "<stdin>", line 5, in foo
TypeError: cannot concatenate 'str' and 'int' objects
```
