
### Servlet 接口
```java
   init()
   getServletConfig()
   service(ServletRequest req, ServltResponse res) throws xxxException
   getServletInfo()
   destroy()
```
* 加载Servlet类的时候调用init方法 (比如Spring MVC的DispatcherServlet)
* 卸载是调用destroy方法
* ServletConfig封装Servlet的初始化参数

### Servlet 容器
#### 工作流程
* HTTP服务器会用一个ServletRequest对象吧请求信息封装
* 调度Servlet的service方法
* 返回ServletResponse对象

#### 扩展机制
* Filter
* Listener

#####  问题1
```
文章中提到
1.SpringMVC 容器实在DispatcherServlet中init方法里创建的。
2.Spring 容器是通过Listener创建的
a、就是说SpringMVC容器和Spring容器还不一样，那么他们是什么关系？
b、他们和Servlet容器又是啥关系？
```
```
Tomcat&Jetty在启动时给每个Web应用创建一个全局的上下文环境，这个上下文就是ServletContext，其为后面的Spring容器提供宿主环境。

Tomcat&Jetty在启动过程中触发容器初始化事件，Spring的ContextLoaderListener会监听到这个事件，它的contextInitialized方法会被调用，在这个方法中，Spring会初始化全局的Spring根容器，这个就是Spring的IoC容器，IoC容器初始化完毕后，Spring将其存储到ServletContext中，便于以后来获取。

Tomcat&Jetty在启动过程中还会扫描Servlet，一个Web应用中的Servlet可以有多个，以SpringMVC中的DispatcherServlet为例，这个Servlet实际上是一个标准的前端控制器，用以转发、匹配、处理每个Servlet请求。

Servlet一般会延迟加载，当第一个请求达到时，Tomcat&Jetty发现DispatcherServlet还没有被实例化，就调用DispatcherServlet的init方法，DispatcherServlet在初始化的时候会建立自己的容器，叫做SpringMVC 容器，用来持有Spring MVC相关的Bean。同时，Spring MVC还会通过ServletContext拿到Spring根容器，并将Spring根容器设为SpringMVC容器的父容器，请注意，Spring MVC容器可以访问父容器中的Bean，但是父容器不能访问子容器的Bean， 也就是说Spring根容器不能访问SpringMVC容器里的Bean。说的通俗点就是，在Controller里可以访问Service对象，但是在Service里不可以访问Controller对象。
```
##### servlet容器的Filter跟 spring 的intercepter 有啥区别
```
Filter是Servlet规范的一部分，是Servlet容器Tomcat实现的。Intercepter是Spring发明的。它们的执行顺序是：


Filter.doFilter();

HandlerInterceptor.preHandle();

Controller

HandlerInterceptor.postHandle();

DispatcherServlet 渲染视图

HandlerInterceptor.afterCompletion();

Filter.doFilter(); Servlet方法返回
```