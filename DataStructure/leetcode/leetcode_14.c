#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/**
 * 最长公共子串 
 */ 
char * longestCommonPrefix(char ** strs, int strsSize){ 
	char * str = (char*)malloc(128);
	memset(str, 0, 128);
	if(strsSize <= 1) {
		return *(strs);
	}
	int i, j = 0;
	while(1)
	{
		i = 0;
		while(i < strsSize - 1)
		{
			if(strs[i][j] != strs[i+1][j])
			{
				return str;
			}
			i++;
		}
		str[j] = strs[0][j];
		j++;
	}
	return str;
}

int main()
{
	char *strs[3] = {"flower","flow","flight"};
	char * str = longestCommonPrefix(strs,3);
	printf("%s\n",str);
	exit(0);
}
