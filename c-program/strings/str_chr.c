#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEST_SIZE 40


int main()
{
	char str[] = "finding first and last occurrence of a character is amazing";
	char alpha[] = "abcdefghijklmnopqrstuvwxyz";

	printf("String to search: %s\n", str);
	printf("Length of string: %d\n", strlen(str));
	printf("Char: first last\n");

	int i;
	for (i = 0; i < strlen(alpha); i++)
	{
		char *position_ptr = strchr(str, alpha[i]);
		char *r_position_ptr = strrchr(str, alpha[i]);

		int position = (position_ptr == NULL ? -1 : position_ptr - str);
		int r_position = (r_position_ptr == NULL ? -1 : r_position_ptr - str);

		printf("%4c: %4d %4d %s\n", alpha[i], position, r_position, position_ptr);
	}

	//find 数字 
	char *ptr = strpbrk("finding digits where there could be digit 5236 is amazing", "0123456789");
	printf("'%s'\n", ptr);
	exit(0);
}	

