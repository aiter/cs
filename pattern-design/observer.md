## 观察者 observer
> subject 被观察者。 subject不知道具体的observer要做是啥。通过标准的接口把数据通知observers
> 每个oberser既可以是线程内处理，也可以是新线程异步处理。
> * 通知到后，oberser觉得处理逻辑和是否处理
> * 实践中，也需要考虑降级、熔断

* 订阅-发布
* 进程内的观察者
> * 发布者，通过接口调用通知订阅者
> * 同步
> * 异步
* 分布式的观察者
> * 将消息发送到消息中间件 
> * 各订阅者，消费消息

### 缺点
* 代码级别
> 由于没有直接的代码调用关系，阅读代码时，比较难找到入口
