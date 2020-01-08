https://www.geeksforgeeks.org/linear-search/

## 线性搜索
问题: 给定一个由n个元素组成的数组arr[],写一个方法在数组arr[]中查找给定的元素x.
实例:
```
输入 : arr[] = {10, 20, 80, 30, 60, 50, 
                     110, 100, 130, 170}
          x = 110;
输出 : 6
元素x(100)在数组的下标为6

输入 : arr[] = {10, 20, 80, 30, 60, 50, 
                     110, 100, 130, 170}
           x = 175;
输出: -1
元素x(175)不在数组arr[]中
```

### 一种简单的线性搜索方法：
* 从最左边开始，依次和x比较
* 如果找到x值，返回下标
* 如果没有找到任何值，返回-1
![](./Linear_Search.png)
![](Linear-Search.png)


```c
// C++ code to linearly search x in arr[]. If x 
// is present then return its location, otherwise 
// return -1 

#include <stdio.h> 

int search(int arr[], int n, int x) 
{ 
	int i; 
	for (i = 0; i < n; i++) 
		if (arr[i] == x) 
			return i; 
	return -1; 
} 

int main(void) 
{ 
	int arr[] = { 2, 3, 4, 10, 40 }; 
	int x = 10; 
	int n = sizeof(arr) / sizeof(arr[0]); 
	int result = search(arr, n, x); 
	(result == -1) ? printf("Element is not present in array") 
				: printf("Element is present at index %d", 
							result); 
	return 0; 
} 

```

```python
# Python3 code to linearly search x in arr[]. 
# If x is present then return its location, 
# otherwise return -1 

def search(arr, n, x): 

	for i in range (0, n): 
		if (arr[i] == x): 
			return i; 
	return -1; 

# Driver Code 
arr = [ 2, 3, 4, 10, 40 ]; 
x = 10; 
n = len(arr); 
result = search(arr, n, x) 
if(result == -1): 
	print("Element is not present in array") 
else: 
	print("Element is present at index", result); 

```

输出：
> Element is present at index 3

时间复杂度为：O(n)
线性搜索很少用到实际生产环境中，因为其他的搜索算法，比如二分查找、hash表都有更快的搜索速度。


