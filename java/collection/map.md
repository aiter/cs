### hashmap & LinkedHashMap
* hashmap 使用数组+链表(红黑树)
* LinkedHashMap 每个节点组成一个双向链表。
> * 新建的node会添加在双向链表的尾部
> * accessOrder=true的话，获取一个node后，会将该node移到双向链表的尾部

![linkedhashmap](linkedhashmap.png)