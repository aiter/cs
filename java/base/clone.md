### java中可以怎么复制对象
* 重新new一个对象，将old对象值都付给新对象，需要处理内部引用对象的问题
* clone方法
* 使用json转成string，然后在“转”出一个新对象
* 同上一条，可以利用序列化后，再反序列化一个新对象
