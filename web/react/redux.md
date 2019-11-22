### store
> 全局管理state。所有组件与store通信

### 特性
* Single Source of Truth
> 状态都放在store中，view有事件更新store，store有变化，就更新view

* 可预测性
> state + action = new state

* 纯函数更新store
> 输出结果一定是依赖输入。  纯函数
