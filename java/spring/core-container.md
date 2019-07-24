* spring-core
* spring-beans
* spring-context
* spring-expression

* spring-jcl
* spring-tx
> Spring Transaction
* configuration

* spring-web
> RequestContextListener
* spring-webmvc
> DispatcherServlet

* redis
> jedis是redis的java客户端，通过它可以对redis进行操作。与之功能相似的还包括：Lettuce等
* spring-data-redis --> spring-data-keyvalue
> 它依赖jedis或Lettuce，实际上是对jedis这些客户端的封装，提供一套与客户端无关的api供应用使用，从而你在从一个redis客户端切换为另一个客户端，不需要修改业务代码。
> [lettuce or jedis](https://www.jianshu.com/p/c7b4cd47ad65)
> spring-boot-starter-data-redis|--> spring-boot-starter
								|--> spring-data-redis 
								|--> lettuce-core
