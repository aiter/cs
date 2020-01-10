https://www.geeksforgeeks.org/binary-search/

## 二叉搜索
给定一个已排序有n个元素的数组arr,写一个方法来查找arr中是否有x元素。

一个简单的方法是使用线性搜索，单算法时间复杂度为O(n)。另一个方法就是使用二分查找。

二分查找：
   通过重复减半搜索区间来查找已排序的数组。从整个数组开始。如果查找的值小于区间中间值，缩小区间到前半部分，否则缩小区间到后半部分。重复查找的过程直到找到查询的数或者区间变为空。
![](Binary-Search.png)

二分查找适用于已经排好序的数组。时间复杂度可以降低到O(log n)

经过一次比较，我们基本就能忽略一半的数据。
  1. 和中间元素比较要查找的x
  2. 如果相等，返回中间索引值
  3. 如果x大于中间值，那么x只可能出现在右边的一半中，所以查找的范围变为右边的一半。
  4. 乳沟是小于中间值，那就是左边的一半。

#### 递归 Recursive
```c
// C program to implement recursive Binary Search 
#include <stdio.h> 

// A recursive binary search function. It returns 
// location of x in given array arr[l..r] is present, 
// otherwise -1 
int binarySearch(int arr[], int l, int r, int x) 
{ 
	if (r >= l) { 
		int mid = l + (r - l) / 2; 

		// If the element is present at the middle 
		// itself 
		if (arr[mid] == x) 
			return mid; 

		// If element is smaller than mid, then 
		// it can only be present in left subarray 
		if (arr[mid] > x) 
			return binarySearch(arr, l, mid - 1, x); 

		// Else the element can only be present 
		// in right subarray 
		return binarySearch(arr, mid + 1, r, x); 
	} 

	// We reach here when element is not 
	// present in array 
	return -1; 
} 

int main(void) 
{ 
	int arr[] = { 2, 3, 4, 10, 40 }; 
	int n = sizeof(arr) / sizeof(arr[0]); 
	int x = 10; 
	int result = binarySearch(arr, 0, n - 1, x); 
	(result == -1) ? printf("Element is not present in array") 
				: printf("Element is present at index %d", 
							result); 
	return 0; 
} 

```

输出：
> Element is present at index 3

#### 迭代 Iterative

```c
// C program to implement iterative Binary Search 
#include <stdio.h> 

// A iterative binary search function. It returns 
// location of x in given array arr[l..r] if present, 
// otherwise -1 
int binarySearch(int arr[], int l, int r, int x) 
{ 
	while (l <= r) { 
		int m = l + (r - l) / 2; 

		// Check if x is present at mid 
		if (arr[m] == x) 
			return m; 

		// If x greater, ignore left half 
		if (arr[m] < x) 
			l = m + 1; 

		// If x is smaller, ignore right half 
		else
			r = m - 1; 
	} 

	// if we reach here, then element was 
	// not present 
	return -1; 
} 

int main(void) 
{ 
	int arr[] = { 2, 3, 4, 10, 40 }; 
	int n = sizeof(arr) / sizeof(arr[0]); 
	int x = 10; 
	int result = binarySearch(arr, 0, n - 1, x); 
	(result == -1) ? printf("Element is not present"
							" in array") 
				: printf("Element is present at "
							"index %d", 
							result); 
	return 0; 
} 

```

输出：
> Element is present at index 3
