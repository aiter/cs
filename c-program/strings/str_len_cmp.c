#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
	char str[16] = "Look Here";
	//strlen 是字符串的实际长度，不是内存占用的长度
	printf("Number of characters in \'%s\' is %d\n", str, strlen(str));


	char str1[] = "Look Here";
	char str2[] = "Look Here";
	int i = strcmp(str1, str2);
	printf("expect{0},output:%d\n",i);

	char str3[] = "Look HerE";
	char str4[] = "Look Here";
	int i0 = strcmp(str3, str4);
	printf("expect{-XX},output:%d\n",i0);
	int i1 = strcmp(str4, str3);
	printf("expect{XX},output:%d\n",i1);

	char str5[] = "Look Hera";
	char str6[] = "Look Her";
	int i2 = strcmp(str5, str6);
	printf("expect{XX},output:%d\n",i2); //ASSIC 码的差值
	int i3 = strcmp(str6, str5);
	printf("expect{-XX},output:%d\n",i3);

	/**
	 * strncmp 比较前n个字符
	 */
	printf("%d\n", strncmp(str3, str4, 8));
	printf("%d\n", strncmp(str3, str4, 9));
	exit(0);
}
