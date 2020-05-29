###语法糖

* 循环for-each
```java
        for (String tmp : list) {
            System.out.println(tmp);
        }
```

* 编译器
```java
        String tmp;
        while(iterator.hasNext()) {
            tmp = (String)iterator.next();
            System.out.println(tmp);
        }

```

### 循环过程中，需要删除元素
* 使用list的remove(Object)接口
> * for-each 循环时(iterator)，再从list操作(list),fail-fast 异常
> * 普通循环，再remove，都是操作list,不会有异常

* 使用iterator的remove()
> for-each，都是iterator的操作，不会有异常。

### fail-fast
> 是在iterator中发生的,只是在这里检查
```java
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
```
* list中的操作，会修改modCount。所以从list中对集合有修改
