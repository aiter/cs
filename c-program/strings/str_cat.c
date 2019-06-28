#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEST_SIZE 40

int main()
{
	char src[] = "Look Here";
	char dest[DEST_SIZE] = "Unimaginable";

	strcat(dest, src);
	printf("%s\n", dest);

	strcpy(dest, "Unimaginable");
	printf("%s\n", dest);
	char *ps = src + 4;
	char *pd = dest + 6; //都是链接在"Unimaginable"的后面，不会从第6个开始
	strcat(pd, ps);
	printf("%s\n", dest);


	exit(0);
}	

