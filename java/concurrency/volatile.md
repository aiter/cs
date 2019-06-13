
### volatile

#### Happens-Before (volatile)

> volatile 解决多线程之间的可见性，读一定是总主存(main memory)中读取，写一定会写回主存。但是并不能解决多线程对共享数据的同步操作问题。

### 性能问题
> 由于使用volatile的变量，必须从主存读和写回主存，不能使用memory cache，所以效率会下降，所以在使用场景中，必须使用在选择。
