###  内置宏
```
查看所有的内置规则
make -p

 gcc -MM main.c 2.c 3.c   依赖清单
```

> 1. $? 当前目标所依赖的文件列表中比当前目标文件还要新的文件
> 2. $@ 当前目标的名字
> 3. $< 当前依赖文件的名字
> 4. $* 不包括后缀名的当前依赖文件的名字
> 5. -：告诉make命令忽略所有错误
> 6. @：告诉make命令在执行某条命令前不要将该命令显示在标准输出上