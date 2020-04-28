## annotation 注解
### Configuration
> 被Configuration 注解的类，可以使用方法在方法上添加 Bean注解来创建被spring管理的bean对象
```
@Configuration
   public class AppConfig {
  
       @Bean
       public MyBean myBean() {
           // instantiate, configure and return bean ...
       }
   }
```

### Bean
> Indicates that a method produces a bean to be managed by the Spring container.
> 指定注解一个方法，这个方法生产一个被Spring容器管理的bean对象
* 在Method上的注解
* 和Configuration一起使用，Configuration注解的类，Bean注解类的方法
* 如果不在Configuration注解的类中，那么就是lite模式

## profile 

