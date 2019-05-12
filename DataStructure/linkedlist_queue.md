> 队列和链表没分清，是怎么回事？
> 
> 有没有？有！真有！

直接看看特性
### 链表
>  Linked List
>  链表通常由一连串节点组成，每个节点包含任意的实例数据（data fields）和一或两个用来指向上一个/或下一个节点的位置的链接（"links"）
> 主要和数组对比。也是一种基础数据结构，一种线性表，但不按线性的顺序存储数据，而是在每一个节点里存到下一个节点的指针(Pointer)。
> 　链表结构可以克服数组链表需要预先知道数据大小的缺点，链表结构可以充分利用计算机内存空间，实现灵活的内存动态管理。但是链表失去了数组随机读取的优点，同时链表由于增加了结点的指针域，空间开销比较大。

*  add(object)
*  get(index)
*  find(object)
*  remove/delete(object)
### 队列
> queue。 
> 队列可以使用数组、链表来实现
>  最常用的场景，从队列的尾部加入数据，从队列的头部取走数据。FIFO(First In First Out)
> java中常用的实现 LinkedList。 java中优先队列PriorityQueue，是用堆这种数据结构实现的。

* dequeue()
* enqueue(object)
Java中
* add()/offer()    //enqueue
* remove()/poll()   // dequeue
C++ stl中
* pop() //dequeue
* push() //enqueue
### redis的list
Redis的list，你说他是链表还是队列？为啥叫list，不叫queue？
他有如下常用的操作：
* lpush/rpush
* lpop/rpop
* bloop/brpop

他的文档这样描述：
> Redis lists are implemented via Linked Lists. //Redis的list用链表实现
> Lists have a special feature that make them suitable to implement queues//List很适合实现队列

## 结论
主要还是看一些特性，**队列**的特性比较明确，比如他基本是操作两端的数据（基本不会操作中间的数据）。就像优先队列使用堆实现，但是他给出来的接口，依然是一个能操作“两端”数据的数据结构。而**链表**是更基础的数据结构，很多数据结构（队列/树/图等）都可以用链表来实现，他更应该和数组的特性去对比。 
