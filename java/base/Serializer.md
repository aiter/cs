### jdk Serializer

#### 如何让一个类支持序列化
实现  java.io.Serializable

添加： private static final long serialVersionUID = 8683452581122892189L;

#### 自己如何自定义序列化
private void writeObject(java.io.ObjectOutputStream s)
private void readObject(java.io.ObjectInputStream s)

#### ArrayList 等集合类是如何序列化的
ArrayList 不是使用默认的序列化方式。
他将内部的数组设置transient：
transient Object[] elementData; 
不会直接序列化这个数组，而是有特殊的实现

```
private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
}
```
