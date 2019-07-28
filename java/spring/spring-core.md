### IOC
> Inversion of Control (IoC) container

### AOP
> Aspect-Oriented Programming (AOP) 
> integration with AspectJ

## The IoC Container
> `org.springframework.beans` and `org.springframework.context` packages are the basis for Spring Framework’s IoC container. 
 * BeanFactory  provides the configuration framework and basic functionality
 * ApplicationContext

### configuration metadata is represented
 * XML
> <beans>
 * java annotations
 * java code
> @Configuration
> @Bean

### objects
* service layer objects
* data access objects(DAOs)
* presentation objects(such as Struts Action instances)
* infrastructure objects(such as Hibernate SessionFactories)
* JMS Queues

### Bean
* Class
* Name
* Scope
* Constructor arguments
* Properties
* Autowiring mode
* Lazy initialization mode
* Initialaztion method
* Destruction method
