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

```
// Java Program to demonstrate the 
// working of Abstract Factory Pattern 

enum CarType 
{ 
	MICRO, MINI, LUXURY 
} 

abstract class Car 
{ 
	Car(CarType model, Location location) 
	{ 
		this.model = model; 
		this.location = location; 
	} 

	abstract void construct(); 

	CarType model = null; 
	Location location = null; 

	CarType getModel() 
	{ 
		return model; 
	} 

	void setModel(CarType model) 
	{ 
		this.model = model; 
	} 

	Location getLocation() 
	{ 
		return location; 
	} 

	void setLocation(Location location) 
	{ 
		this.location = location; 
	} 

	@Override
	public String toString() 
	{ 
		return "CarModel - "+model + " located in "+location; 
	} 
} 

class LuxuryCar extends Car 
{ 
	LuxuryCar(Location location) 
	{ 
		super(CarType.LUXURY, location); 
		construct(); 
	} 
	@Override
	protected void construct() 
	{ 
		System.out.println("Connecting to luxury car"); 
	} 
} 

class MicroCar extends Car 
{ 
	MicroCar(Location location) 
	{ 
		super(CarType.MICRO, location); 
		construct(); 
	} 
	@Override
	protected void construct() 
	{ 
		System.out.println("Connecting to Micro Car "); 
	} 
} 

class MiniCar extends Car 
{ 
	MiniCar(Location location) 
	{ 
		super(CarType.MINI,location ); 
		construct(); 
	} 
	
	@Override
	void construct() 
	{ 
		System.out.println("Connecting to Mini car"); 
	} 
} 

enum Location 
{ 
DEFAULT, USA, INDIA 
} 

class INDIACarFactory 
{ 
	static Car buildCar(CarType model) 
	{ 
		Car car = null; 
		switch (model) 
		{ 
			case MICRO: 
				car = new MicroCar(Location.INDIA); 
				break; 
			
			case MINI: 
				car = new MiniCar(Location.INDIA); 
				break; 
				
			case LUXURY: 
				car = new LuxuryCar(Location.INDIA); 
				break; 
				
				default: 
				break; 
			
		} 
		return car; 
	} 
} 

class DefaultCarFactory 
{ 
	public static Car buildCar(CarType model) 
	{ 
		Car car = null; 
		switch (model) 
		{ 
			case MICRO: 
				car = new MicroCar(Location.DEFAULT); 
				break; 
			
			case MINI: 
				car = new MiniCar(Location.DEFAULT); 
				break; 
				
			case LUXURY: 
				car = new LuxuryCar(Location.DEFAULT); 
				break; 
				
				default: 
				break; 
			
		} 
		return car; 
	} 
} 


class USACarFactory 
{ 
	public static Car buildCar(CarType model) 
	{ 
		Car car = null; 
		switch (model) 
		{ 
			case MICRO: 
				car = new MicroCar(Location.USA); 
				break; 
			
			case MINI: 
				car = new MiniCar(Location.USA); 
				break; 
				
			case LUXURY: 
				car = new LuxuryCar(Location.USA); 
				break; 
				
				default: 
				break; 
			
		} 
		return car; 
	} 
} 



class CarFactory 
{ 
	private CarFactory() 
	{ 
		
	} 
	public static Car buildCar(CarType type) 
	{ 
		Car car = null; 
		// We can add any GPS Function here which 
		// read location property somewhere from configuration 
		// and use location specific car factory 
		// Currently I'm just using INDIA as Location 
		Location location = Location.INDIA; 
		
		switch(location) 
		{ 
			case USA: 
				car = USACarFactory.buildCar(type); 
				break; 
				
			case INDIA: 
				car = INDIACarFactory.buildCar(type); 
				break; 
					
			default: 
				car = DefaultCarFactory.buildCar(type); 

		} 
		
		return car; 

	} 
} 

class AbstractDesign 
{ 
	public static void main(String[] args) 
	{ 
		System.out.println(CarFactory.buildCar(CarType.MICRO)); 
		System.out.println(CarFactory.buildCar(CarType.MINI)); 
		System.out.println(CarFactory.buildCar(CarType.LUXURY)); 
	} 
} 


```

### 优点 Advantages
* 隔离了实现类
* 替换具体的实现工厂方便
* 产品一致。相关的产品来自同一个工厂类

### 缺点 Disadvantages
* 新增产品不方便
