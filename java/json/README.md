[java-json](http://tutorials.jenkov.com/java-json/index.html)
> JavaScript Object Notation. the browsers can parse JSON into JavaScript objects natively.

* Jackson
> * Jackson Core
> * Jackson Annotations
> * Jackson Databind
* Boon
* GSON
* json.org
* fastjson

### Jackson使用
* 将java对象转化为json字符串(序列化)
* 将json字符串转换为java对象(反序列化)
```java
    1. 只有set方法，没有对应的getXxx，序列化为json时，没有对应的json-key(xxx)
    2. 可以在field上，加{@link JsonProperty},会有对应的json-key(field name)
    3. 可以在method上，加{@link JsonProperty},会有对应的json-key(method name)
    4. 可以在{@link JsonProperty} 里设置value,也就是json-key

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr =objectMapper.writeValueAsString(jsonItem);
```
* transient 修饰的字段不被序列化
* static 修饰的字段不被序列化

JsonFactory
> 用来创建 JsonParser  JsonGenrator
TypeFactory 
> 用来创建 JavaType的实例
> * 如果是Class
>  SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT); Class<?> CLS_OBJECT = Object.class;
> * 如果是 ParameterizedType
> * 如果是 JavaType(jackson)
> * 。。。GenericArrayType
> * TypeVariable
> * WildcardType

### fastjson & jackson 区别
| data-binding | private字段 | public字段 | private方法 | public方法 | 默认值 | 非getXxx方法| 无参数构造器 |transient字段|static字段|
| --- | --- | --- | --- | --- | --- | --- | --- | --- |--- | 
|fastjson| 不输出 | 输出 | 不输出 | 输出 | string是null时不输出 | @JsonProperty(value = "xxx") | 支持@JSONCreator | 不输出|不输出|
|jackson | 输出(必须@JsonProperty)   | 输出 | 输出(必须@JsonProperty) | 输出 | 输出null| @JSONField(name = "xxx")| 支持@JsonCreator |不输出|不输出|

Class
> 实现了 Serializable、GenericDeclaration、Type、AnnotatedElement