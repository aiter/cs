#include <stdlib.h>
#include "a.h"

extern void f_two();
extern void f_three();

int main() {
	f_two();
	f_three();
	exit(0);
}
