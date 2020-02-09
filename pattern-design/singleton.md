## 为什么要使用单例
> Singleton Design Pattern

有些场景，不需要创建多个对象，整个“容器”中，有个一对象就够了。比如：配置信息、解决资源访问冲突的情况。

## 如何实现
* 构造函数private，避免外部创建对象
* 创建时的线程安全问题
* 是否支持延迟加载(第一次使用对象时，才真正的创建)
* 考虑获取对象时的性能(是否加锁)

* 饿汉式
> 类加载时，就创建对应的对象了

* 懒汉式
> 延迟加载，使用时才创建对象
> * 考虑是否加锁

* 懒汉式双重检测
> 只要对象已经被创建了，就直接返回。不在加速。

* 静态内部类
> 支持延迟加载，只支持高并发外部类加载时，不会创建对象。getInstance时，才加载内部类和初始化外部对象。
```java
   public class Singleton {
       private Singleton() {}
       
       private static class SingletonInner {
          private static final Singleton instance = new Singleton();
       }
       
       public static Singleton getInstance() {
             return SingletonInner.instance;
       }
       
       public void test() {
          
       }
```

* 枚举
> 本身保证线程安全和实例的唯一性

## 使用单例的问题
* 违背基于接口二非实现的设计原则
* 对面向对象的继承、多态特性支持不友好
* 会隐藏类之间的依赖关系
* 扩展性不好
* 可测试性不好
* 不支持有参数的构造函数


