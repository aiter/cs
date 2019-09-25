* 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
* 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
* 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
* 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

### function & stream
@FunctionalInterface
* Consumer<T>
> void accept(T t);
* Function<T, R>
> R apply(T t);
* Predicate<T>
> boolean test(T t);
* Supplier<T>
> T get();
```java
    public static void main(String[] args) {

        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("aiter");

        Supplier<List<String>> supplier = () -> new ArrayList<String>() {{
            add("6");
            add("80");
            add("70go");
        }};

        Predicate<String> predicate = str -> str.length() > 1;

        Function<String, String> function = str -> str + "_tail";

        supplier.get()
            .stream()
            .filter(predicate)
            .map(function)
            .forEach(consumer);
    }
```
