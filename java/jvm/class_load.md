### 类加载

| 1 | 2 | 3 |
| --- | --- | --- |
|Loading | Verification  Preparation  Resolution |Initialization | 
| | Linking | |  | |

#### Loading 加载
> 类加载器查找字节流(class)
* 启动类加载器
> C++实现，JVM中没有对应的对象
>
> 其他的类加载器，都是java.lang.ClassLoader的子类
> 
> 双亲委派，加载类时，先将请求发送给父类加载器，父类没有找到请求的类时，该加载器才会尝试去加载

* 扩展类加载器（Extension class loader）
> java 9之后更名为platform class loader
* 应用类加载器 (Application class loader)

#### linking 链接
> 三步： 验证，准备，解析



### import的都加载吗？
> 无关，只有在真正使用时，才会加载。

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test2 {
    public static void main(String [] args) {
        System.out.println(1);
    }

    public void test() {
        Log log =  LogFactory.getLog("dd");
    }
}
```
test()中使用了LogFactory，Log 类，执行Test2时，并没有调用test()方法，所以classloader不会加载这2个类
。
spring-jcl.jar 适配日志时，就会根据类是否可加载，来适配对应的日志。jcl中有依赖log4j-api、slf4j-api，但是classpath中
没有对应的jar，jcl也可以运行

### 虚方法调用 
实例方法：方法表