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
[Java Copy Constructor](https://www.baeldung.com/java-copy-constructor)

#### 设计缺陷
* Cloneable 接口并没有一个clone方法。而且调用clone()方法时，需要处理CloneNotSupportedException异常
* 不是深复制。都是原始类型(private types)或者不可变类(immutable types)时，没有问题。
* 如果要深复制，需要在clone方法中特别处理(主要是处理引用对象的复制)，如果应用的对象，本身并没有实现Cloneable接口，并重写clone()方法，需要单独new对象(处理更复制)
* clone方法中，不能初始化final字段的值
* clone方法是返回Object的引用，需要强制的类型转换，如`(Item)super.clone()`

#### 那怎样复制对象
* 复制构造器 Copy Constructor
> * 创建一个对象时，使用另一个相同类的对象
> * 自行决定如何复制对象
> * 不需要把相关逻辑写在其他类中(比如工厂方法中)，封装性更好，也更不容易出错(比如新增字段后)
> * 深复制(deep copy)。有引用类型，可以新创建对象来达到深复制
> * 构造器中，可以初始化final字段的值
> * 复制构造器，需要注意继承是的复制构造器
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
> * native方法
> * 不调用对应的构造器
* Boolean & BigInteger
> * 浅复制。复制对象指向同一个对象
