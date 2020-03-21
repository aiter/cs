## 策略模式 Strategy
> 定义个算法族，可以方便的相互替换

```java
  public interface Strategy {
	void algorithmA();
  }

  public class ConcreteStrategyA implements Strategy {

    	@Override
		public void algorithmA() {
			//
		}
  }
  public class ConcreteStrategyB implements Strategy {

    	@Override
		public void algorithmA() {
			//
		}
  }
```

### 创建
```java

public class StrategyFactory {
  private static final Map<String, Strategy> strategies = new HashMap<>();

  static {
    strategies.put("A", new ConcreteStrategyA());
    strategies.put("B", new ConcreteStrategyB());
  }

  public static Strategy getStrategy(String type) {
    if (type == null || type.isEmpty()) {
      throw new IllegalArgumentException("type should not be empty.");
    }
    return strategies.get(type);
  }
}
```
