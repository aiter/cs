
##如何写代码

```
// 1
public void test(String name) {
   if(null != nanme) {
        XXXX
   }
}
// 2
public void test(String name) {
   if(null == nanme) {
        return;
   }
   XXXX
}
```
> 方法2，需要测试传递null, 才能有对应热return这一行的覆盖率。 当然，最重要的是也业务逻辑的覆盖。这里只讨论相同的UT，可能对覆盖率的影响。
