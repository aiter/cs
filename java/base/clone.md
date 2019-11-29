### java中可以怎么复制对象
* 重新new一个对象，将old对象值都付给新对象，需要处理内部引用对象的问题
* clone方法
* copy constructor
* 使用json转成string，然后在“转”出一个新对象
* 同上一条，可以利用序列化后，再反序列化一个新对象


#### clone
```java
//实现java.lang.Cloneable 接口
public class Item implements Cloneable {

	// 重载Object的clone方法。
    // 调用super.clone()
    // throws CloneNotSupportedException
    @Override
	public Item clone() throws CloneNotSupportedException {
        return (Item)super.clone();
    }
}
//内部的引用复制是浅复制
```

#### copy constructor
```java
public class Employee {
    private int id;
    private String name;
     
    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
    }
}
```
