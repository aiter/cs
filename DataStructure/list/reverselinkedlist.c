#include<stdio.h> 
#include<stdlib.h>

struct Node
{
	int data;
	struct Node * next;
};

/**
 *      c       
 *    +---+   +---+   +---+   +---+
 *    | 5 |-->| 4 |-->| 7 |-->| 9 | 
 *    +---+   +---+   +---+   +---+
 *    括号中的数字代表第几步
 *      c->n(2) n(1)
 *      p(3)    c(4)
 *    +---+   +---+   +---+   +---+
 *    | 5 |-->| 4 |-->| 7 |-->| 9 | 
 *    +---+   +---+   +---+   +---+
 */
static void reverse(struct Node ** head_ref)
{
	struct Node* prev = NULL;
	struct Node* current = *head_ref;
	struct Node* next = NULL;
	while(current != NULL) {
		next = current->next;
		current->next = prev;
		prev = current;
		current = next;
	}	
	*head_ref = prev;
}

void push(struct Node ** head_ref, int new_data)
{
	struct Node * new_node = (struct Node*)malloc(sizeof(struct Node));
	new_node->data = new_data;
	new_node->next = (*head_ref);
	(*head_ref) = new_node;
}

void printList(struct Node* head) 
{
	struct Node *tmp = head;
	while(tmp != NULL)
	{
		printf("%d ", tmp->data);
		tmp = tmp->next;
	}
}

int main(void)
{
	struct Node* head = NULL;
	push(&head, 20); 
	push(&head, 14); 
	push(&head, 5);  
	push(&head, 12); 
	
	printf("Given linked list\n"); 
	printList(head);     
	reverse(&head);                       
	printf("\nReversed Linked list \n"); 
	printList(head);     
	getchar(); 	
}
