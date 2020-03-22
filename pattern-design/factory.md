## 工厂模式
> Factory Design Pattern

* 简单工厂
* 工厂方法
* 抽象工厂

GoF中，简单工厂是工厂方法的一种特例。

### 简单工厂 Simple Factory
> 如果不经常变换，完全没问题。

### 工厂方法 Factory Method
> 更符合开闭原则，不需要修改原代码，而是扩展一个新的工厂。
> 为工厂创建一个工厂
```java
   public interface Document{
		void show();
	}

   public class TextDocument implements Document {
		public void show(){
			System.out.println("show text");
		}
	}

   public class PicDocument implements Document {
		public void show(){
			System.out.println("show picture");
		}
	}

```
### 工厂类
```java

	public interface DocumentFactory {
		Document createDocument(String type);
	}

	public MyDocumentFactory implements DocumentFactory {
		Document createDocument(String type) {
			if("text".equals(type)) {
				return new TextDocument();
			}
			return new PicDocument();
		}
	}

```

### 使用类
```java
	public class App() {
		public static void main(String [] args) {
			DocumentFactory documentFactory = new MyDocumentFactory();
			Document document = documentFactory.createDocument(1);

			document.show();
		}
```

### 抽象工厂 Abstract Factory
> 使用比较少
