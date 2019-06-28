#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEST_SIZE 40

int main()
{
	char src[] = "Look Here";
	char dest[DEST_SIZE] = "Unimaginable";

	strcpy(dest, src);
	printf("%s\n", dest);

	char *p = dest + 5;
	strcpy(p, src);
	printf("%s\n", dest);

	char *ps = src + 4;
	char *pd = dest + strlen(
			dest);
	strcpy(pd, ps);
	printf("%s\n", dest);

	exit(0);
}	

