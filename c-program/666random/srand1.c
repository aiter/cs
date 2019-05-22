// C program to generate random numbers 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include<time.h> 

// Driver program 
int main(void) 
{ 
	// This program will create different sequence of 
	// random numbers on every program run 

	// Use current time as seed for random generator 
	srand(time(0)); 
	//srand(10);
	const int LEN = 10;
	int arr[LEN];
	memset(arr, 0, LEN*sizeof(arr[0])); 

	int i;
	for(i = 0; i<50000; i++) {
		//printf(" %d ", rand());
		arr[rand()%LEN]++;
	} 
	printf("\n");

	int j;
	for(j = 0; j < LEN; j++) {
		printf(" %d ", arr[j]);
	}
	printf("\n");

	return 0; 
} 

