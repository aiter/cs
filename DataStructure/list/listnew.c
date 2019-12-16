#include<stdio.h>
#include<stdlib.h>

struct Node
{
	int data;
	struct Node * next;
};

static void reverse(struct Node ** head_ref)
{
	struct Node * prev = NULL;
	struct Node * current = *head_ref;
	struct Node * next = NULL;
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
	new_node->next = *head_ref;
	*head_ref = new_node;
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
	struct Node * head = NULL;
	push(&head,1);
	push(&head,10);
	push(&head,21);
	push(&head,131);
	push(&head,104);

	printList(head);
	reverse(&head);
	printf("\nReversed Linked list \n"); 
	printList(head);
	getchar();
}
