/* strncmp example */
#include <stdio.h>
#include <string.h>

int main ()
{
  char str[][5] = { "R2D2" , "C3PO" , "R2A6" };
  int n;
  puts ("Looking for R2 astromech droids...");
  for (n=0 ; n<3 ; n++) {
	  printf ("%s,found %d\n",str[n],strncmp(str[n],"R2xxxxxx",2));
	  if (strncmp (str[n],"R2xx",2) == 0)
	  {
		  printf ("found %s\n",str[n]);
	  }
  }
  return 0;
}
