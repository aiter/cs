### MyDate
```java
public class MyDate extends Date {

    public MyDate() {
    }

    // 覆盖finalize()方法
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("obj [Date: " + this.getTime() + "] is gc");
    }

    @Override
    public String toString() {
        return "Date: " + this.getTime();
    }
}
```
### ReferenceTest
```java
public class ReferenceTest {
    public ReferenceTest() {
    }

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1024 * 100];
        for(int i = 0; i < 1024 * 100; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }
        }
    }
}
```
### 强引用

```java
public class StrongReferenceTest {

    public static void main(String[] args) {
        MyDate date = new MyDate();
        System.gc();
    }
}
```
### 软引用
```java
public class SoftReferenceTest {

    public static void main(String[] args) {
		//使用软引用
        SoftReference ref = new SoftReference(new MyDate());
        ReferenceTest.drainMemory();


        System.out.println(ref.get());
        System.out.println(" SoftReferenceTest end");
    }
}
```

```shell
$java -cp . -Xms100m -Xmx100m  SoftReferenceTest
Date: 1559822489419
 SoftReferenceTest end
$java -cp . -Xms12m -Xmx12m  SoftReferenceTest
//内存不够了，就会gc回收软引用对象
obj [Date: 1559822540494] is gc
null
 SoftReferenceTest end
```

### 弱引用
```java
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference ref = new WeakReference(new MyDate());
        System.out.println(ref.get());
        System.gc();
        System.out.println(ref.get());
        System.out.println("end");
    }
}
```
```shell
$java -cp . -Xms12m -Xmx12m  com.jihaoxian.demo.reference.b.WeakReferenceTest
Date: 1559822842196
null
end
obj [Date: 1559822842196] is gc
$java -cp . -Xms120m -Xmx120m  com.jihaoxian.demo.reference.b.WeakReferenceTest
Date: 1559822908851
null
end
obj [Date: 1559822908851] is gc
```

### 幻想引用
```java
public class PhantomReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.out.println(ref.get());
        System.gc();
        System.out.println(queue.poll());
        System.out.println(ref.get());
    }
}
```

```shell
$java -cp . -Xms120m -Xmx120m  com.jihaoxian.demo.reference.b.PhantomReferenceTest
null
null
null
obj [Date: 1559823154588] is gc
```
