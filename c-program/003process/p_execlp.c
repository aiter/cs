#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	printf("Running ps with execlp\n");
	execlp("ps", "ps", "ax", 0);
    // 不执行下面的逻辑
	printf("Done\n");
	exit(0);
}
