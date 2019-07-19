### 类加载

Loading --> Verification --> Preparation --> Resolution --> Initialization --> Using --> Unloading
		|				        Linking                |

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

