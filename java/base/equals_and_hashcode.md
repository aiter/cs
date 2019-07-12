```java
public class Simple1 {
    private String name;
    private int age;
}
```

```java
/**
* lombok 自动生成的equals 和 hashcode
*/
public class Simple1 {
    private String name;
    private int age;

    public Simple1() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Simple1)) {
            return false;
        } else {
            Simple1 other = (Simple1)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.name;
                Object other$name = other.name;
                if (this$name == null) {
                    if (other$name == null) {
                        return this.age == other.age;
                    }
                } else if (this$name.equals(other$name)) {
                    return this.age == other.age;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Simple1;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.name;
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        result = result * 59 + this.age;
        return result;
    }
}
```

### 添加List和数组
> 注意如何使用深度复制。数组、容器类、复杂对象
> Arrays.deepEquals(this.scores, other.scores)
> Arrays.deepHashCode(this.scores)
> AbstractList中的equals实现

```java
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List<?>) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }
```

```java

import java.util.Arrays;
import java.util.List;

public class Simple1 {
    private String name;
    private int age;
    private List<String> nicks;
    private Double[] scores;

    public Simple1() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Simple1)) {
            return false;
        } else {
            Simple1 other = (Simple1)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label43: {
                    Object this$name = this.name;
                    Object other$name = other.name;
                    if (this$name == null) {
                        if (other$name == null) {
                            break label43;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label43;
                    }

                    return false;
                }

                if (this.age != other.age) {
                    return false;
                } else {
                    Object this$nicks = this.nicks;
                    Object other$nicks = other.nicks;
                    if (this$nicks == null) {
                        if (other$nicks != null) {
                            return false;
                        }
                    } else if (!this$nicks.equals(other$nicks)) {
                        return false;
                    }

                    if (!Arrays.deepEquals(this.scores, other.scores)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Simple1;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.name;
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        result = result * 59 + this.age;
        Object $nicks = this.nicks;
        result = result * 59 + ($nicks == null ? 43 : $nicks.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.scores);
        return result;
    }
}
```
