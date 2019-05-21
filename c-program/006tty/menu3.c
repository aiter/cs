#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

char *menu[] = {
	"a - add new record",
	"d - delete record",
	"q - quit",
	NULL,
};

int getchoice(char *great, char *choices[], FILE *in, FILE *out);

int main() {
	int choice = 0;
	FILE *input;
	FILE *output;

	//判断是否是一个tty，不是提示
	if(!isatty(fileno(stdout))) {
		fprintf(stderr, "You are not a terminal,OK.\n");
	}

	input = fopen("/dev/tty", "r");
	output = fopen("/dev/tty", "w");
	if(!input || !output) {
		fprintf(stderr, "unable to open /dev/ttyp.\n");
		exit(EXIT_FAILURE);
	}
	do {
		choice = getchoice("Please select", menu, input, output);
		printf("You have chosen: %c\n", choice);
	}while(choice != 'q');
	exit(EXIT_SUCCESS);
}

int getchoice(char *great, char *choices[], FILE *in, FILE *out) {
	int chosen = 0;
	int selected;
	char **option;

	do{
		fprintf(out, "Choice: %s\n", great);
		option = choices;
		while(*option) {
			fprintf(out, "%s\n", *option);
			option++;
		}
		do {
			selected = fgetc(in);
		} while(selected == '\n');
		option = choices;
		while(*option) {
			if(selected == *option[0]) {
				chosen = 1;
				break;
			}
			option++;
		}
		if(!chosen) {
			fprintf(out, "select again\n");
		}
	}while(!chosen);
	return selected;
}
				
			
		
