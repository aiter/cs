
### 遗传表示
1. 基于箱子的表示(bin-based representation)
> 基因的位置表示物品，基因的值表示该物品放入的箱子。

```
         1   2   3   4   5   6   <--- 物品
       +---+---+---+---+---+---+
染色体 | 1 | 2 | 4 | 5 | 3 | 2 | <--- 箱子
       +---+---+---+---+---+---+
```
> 1号物品放入箱子1，2放入2，3放入4，4放入5，5放入3，6放入2.

特点：
* 颜色体长度恒定，就是物品的数量
* 可以采用标准的遗传算子
缺点：
* 高度冗余（1 2 3 1 4 4）和 (3 1 4 3 2 2) 就是对问题相同解的不同编码。随着箱子数量的增加而指数上升，也就是间接地随问题的规模指数上升。因此遗传算法搜索的空间就比问题本身的解空间大得多。搜索能力受到严重的削弱。
* 会使某个基因的含义依赖于其他的基因。
* 超过其重量限制，产生不可行的解。

2. 基于物品的表示(object-based representation)
> 对物品的排序进行编码，然后应用解码器得到器对应的解。

```
 待放置    1 2 3 4 5 6
 染色体    1 2 3 | 4 5 | 6
 染色体    2 1 3 | 4 5 | 6
 染色体    6 3 | 1 4 2 | 5 

不同的编码，相同的解
```

特点：
* 从不产生不可行解

缺点：
* 也是高度冗余
* 无法在进化过程中，维护从父代继承的信息

3. 基于分组/群体的表示(group-based representation)
