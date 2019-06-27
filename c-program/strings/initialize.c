#include <stdio.h>
#include <stdlib.h>

int main()
{
	/**
	 * 1
	 */
	//read-only,cannot be modified
	char *char_ptr = "Hello here";
	printf("%s\n", char_ptr);

	/**
	 * 2
	 */
	char char_arr[] = "Hello other";
	//this is same
	char char_arr2[] = {'H','e','l','l','o',' ','o','t','h','e','r','\0'};
	char_arr[5] = '_';
	char_arr2[5] = '-';
	printf("%s\n", char_arr);
	printf("%s\n", char_arr2);

	/**
	 * 3
	 * <pre>
	 ------------------------------------------------------------------
	 | H | e | l | l | o |  | o | t | h | e | r | \0 | \0 | \0 | \0 |
	 ------------------------------------------------------------------
	   0   1   2   3   4  5   6   7   8   9   10   11   12   13   14
	 * </pre>
	 */
	char char_arr3[15] = "Hello other";
	char_arr3[11] = '-';
	char_arr3[12] = '-';
	char_arr3[14] = '-';//因为13是\0,所以下一行打印字符串时打印不出来
	printf("%s\n", char_arr3);
	printf("%c\n", char_arr3[14]); //但是内存中存在
	exit(0);
}


