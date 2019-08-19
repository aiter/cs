## 设计原则（Principles）
### DRY-避免重复原则  Don’t Repeat Yourself
* Every piece of knowledge or logic must have a single, unambiguous representation within a system.
* 不要重复地写相同的代码和逻辑
* 拆分代码和逻辑到更小并可重用的方法中，需要时，直接调用；不要写太长的方法
* 【收益】更少的代码；更好维护；减少bug
### KISS-简单原则  Keep it Simple,Stupid
* keep the code simple and clear.保持代码简单、整洁，并容易看懂(理解)
* 方法不要太长，最好单方法不要超过40-50行
* 每个方法应该只解决一个小问题。你写一个方法的Unit Test Case时，是否简单？
### 迪米特原则(最少知识原则) Law of Demeter(LoD) / Principle of Least Knowledge
* 你的方法可以调用该类的其他方法
* 你的方法可以调用成员变量(field)的方法
* 你的方法可以调用你参数的方法
* 你的方法内，创建了对象，可以调用这个对象的方法
* 【不要】不要调用一个全局对象的方法，但是可以通过参数传递对象
* 【不要】不要使用调用链的方式。a.getX().getY().getZ().doSomething().
### 面向对象的S.O.L.I.D原则 (link)
* Single-responsibility Principle  单一职责原则
    * A class should have one and only one reason to change, meaning that a class should have only one job.
* Open-closed Principle (open for extension, but closed for modification)  开闭原则
    * Objects or entities should be open for extension, but closed for modification.
* Liskov substitution Principle   liskov替代原则
    * Let q(x) be a property provable about objects of x of type T. Then q(y) should be provable for objects y of type S where S is a subtype of T.
* Interface segragation principle 接口隔离原则
    * A client should never be forced to implement an interface that it doesn't use or clients shouldn't be forced to depend on methods they do not use.
* Dependency Inversion principle 依赖反转原则
    * Entities must depend on abstractions not on concretions. It states that the high level module must not depend on the low level module, but they should depend on abstractions.

