## 原型模型
> 使用比较少

使用已有对象创建对象。


### java中可以怎么复制对象
* 重新new一个对象，将old对象值都付给新对象，需要处理内部引用对象的问题
* clone方法
* copy constructor
* 使用json转成(序列化)string，然后在“转”(反序列化)出一个新对象
* 同上一条，可以利用序列化后，再反序列化一个新对象