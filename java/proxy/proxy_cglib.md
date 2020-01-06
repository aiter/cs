
## jdk proxy

```java

public interface Car {

    public void run();
}

package proxy;

public class CarImpl implements Car{

    public void run() {
        System.out.println("car running");
    }

}

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * JDK动态代理代理类 
 */
public class TestHandler implements InvocationHandler{
    private Object obj;
    public CarHandler(Object obj){
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object res = method.invoke(obj, args);
        System.out.println("after");
        return res;
    }
}
```
#### main
```java
import java.lang.reflect.Proxy;

public class main {

    public static void main(String[] args) {
        CarImpl carImpl = new CarImpl();
        TestHandler testHandler = new TestHandler(carImpl);
        Car proxy = (Car)Proxy.newProxyInstance(
                main.class.getClassLoader(), //第一个参数，获取ClassLoader
                carImpl.getClass().getInterfaces(), //第二个参数，获取被代理类的接口
                testHandler);//第三个参数，一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
        proxy.run();
    }
}
```

### JDK proxy
* 被代理类，必须实现接口
* 真正的代理逻辑在InvocationHandler的实现中。invoke方法
* 代理，一般的目的是，将一些非业务的逻辑(代码)从业务代码中抽离出来，比如：日志，耗时，权限检查等
* 使用Proxy类构造代理实例对象，使用代理对象调用接口中定义的方法

## Cglib

```java
    public class CglibProxy implements MethodInterceptor {
    private Object animal;

    /**
     * 创建代理对象
     *
     * @param object
     * @return
     */
    public Object getInstance(Object object) {
        this.animal = object;

        //创建Enhancer对象，这里只需要将被代理对象的类对象设置为Superclass
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.animal.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        methodProxy.invokeSuper(obj, args);
        System.out.println("end");
        return null;
    }
}
```

#### main
```java
    public class CglibProxyMain {

        public static void main(String [] args) {
            CglibProxy cglibProxy = new CglibProxy();
            Duck duck = (Duck)cglibProxy.getInstance(new Duck());
            duck.run();;
        }
    }
```
### cglib
* 被代理类，不必实现接口
* 真正的代理逻辑在MethodInterceptor的实现中(callback)。