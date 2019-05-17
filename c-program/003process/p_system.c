#include <stdlib.h>
#include <stdio.h>

int main() {
	printf("Running ps with system\n");
	system("ps ax");
    // system执行完，还要执行下面的逻辑
	printf("Done\n");
	exit(0);
}
