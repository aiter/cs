#include<unistd.h>
#include<stdio.h>
#include<sys/mman.h>
#include<fcntl.h>
#include<stdlib.h>

typedef struct {
	int integer;
	char string[24];
} RECORD;

#define NRECORD (100)

/**
 * 运行结束后，使用od -c recoeds.dat 查看第43条记录，已经被修改为243
 */
int main() {
	RECORD record, *mapped;
	int i, f;
	FILE *fp;

	//stdio
	fp = fopen("recoeds.dat", "w+");
	for(i = 0; i < NRECORD; i++) {
		record.integer = i;
		sprintf(record.string, "RECORD-%d", i);
		fwrite(&record, sizeof(record), 1, fp);
	}
	fclose(fp);

	fp = fopen("recoeds.dat", "r+");
	fseek(fp, 43*sizeof(record), SEEK_SET);
	fread(&record, sizeof(record), 1, fp);

	record.integer = 143;
	sprintf(record.string, "RECORD-%d", record.integer);

	fseek(fp, 43*sizeof(record), SEEK_SET);
	fwrite(&record, sizeof(record), 1, fp);

	fclose(fp);

	//system call
	f = open("recoeds.dat", O_RDWR);
	mapped = (RECORD *)mmap(0, NRECORD * sizeof(record), PROT_READ|PROT_WRITE, MAP_SHARED, f, 0);

	mapped[43].integer = 243;
	sprintf(mapped[43].string, "RECORD-%d", mapped[43].integer);
	
	msync((void *)mapped, NRECORD * sizeof(record), MS_ASYNC);
	munmap((void *)mapped, NRECORD * sizeof(record));
	close(f);

	exit(EXIT_SUCCESS);
}
