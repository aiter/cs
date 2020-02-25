## 泛化 Generalization
> 继承  
```java
public class A {}
public class AA extends A {}
```

## 实现Realization
> 实现接口
```java
public class A {}
public class AA implements A {}
```

## 聚合 Aggregation
> 包含。（成员可独立） B可以独立存活，不跟随A的生命周期
```java
public class A {
    private B b;
    public A(B b) {
         this.b = b;
    }
}
```

## 组合 Composition
> 包含。（成员不可独立） B不可以独立存活，跟随A的生命周期
```java
public class A {
    private B b;
    public A() {
        b = new B();
    }
}
```

## 关联 Association
> 弱关系。 类成员变量存在。

## 依赖 Dependency
> 无法使用其他关系表示
