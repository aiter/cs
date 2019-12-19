### java中可以怎么复制对象
* 重新new一个对象，将old对象值都付给新对象，需要处理内部引用对象的问题
* clone方法
* copy constructor
* 使用json转成(序列化)string，然后在“转”(反序列化)出一个新对象
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
#### 为什么建议不使用clone了
[Copy Constructor versus Cloning](https://www.artima.com/intv/bloch.html#part13)

#### 设计缺陷
* Cloneable 接口并没有一个clone方法
* 不是深复制

#### 那怎样复制对象
* 复制构造器 Copy Constructor
* 静态工厂方法  static factory methods

* json是如何new对象的
> 一般的json工具，需要对象有一个默认的构造器(没有参数的构造器)。Jackson不要求必须有默认的构造器
```
  public class CtorBean
{
  public final String name;
  public final int age;

  @JsonCreator // constructor can be public, private, whatever
  private CtorBean(@JsonProperty("name") String name,
    @JsonProperty("age") int age)
  {
      this.name = name;
      this.age = age;
  }
}
```
* clone是如何new对象的，是否调度用构造器
* Boolean & BigInteger
