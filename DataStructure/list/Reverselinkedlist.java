public Reverselinkedlist {
    static Node head; 
  
    static class Node { 
  
        int data; 
        Node next; 
  
        Node(int d) 
        { 
            data = d; 
            next = null; 
        } 
    } 
  
    /** 
    * Function to reverse the linked list
    * 
    * 单向链表
    */
    Node reverse(Node node) 
    { 
        Node prev = null;
        Node current = node;
        Node next = null;
        // 1. next前移 2. current.next翻转  3. pre前移  4. current前移
        // 结束条件是什么？ 一定是current会不断的后移
        while (current != null) {
            //向链表后移动，会到null
            next = current.next;
            //当前Node的后续node，指向原来的前向节点
            current.next = prev;
            // 注意这一步，避免前序node丢失
            prev = current;


            // 
            current = next;
        }
        node = prev;

        return node;
    }

    public void addFirst(int object) {
        Node newNode = new Node(object);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int object) { // here I don't need a tail reference
        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node(object);
    } 

    // prints content of double linked list 
    void printList(Node node) 
    { 
        while (node != null) { 
            System.out.print(node.data + " "); 
            node = node.next; 
        } 
    } 
  
    public static void main(String[] args) 
    { 
        LinkedList list = new LinkedList(); 
        list.head = new Node(85); 
        list.head.next = new Node(15); 
        list.head.next.next = new Node(4); 
        list.head.next.next.next = new Node(20); 
  
        System.out.println("Given Linked list"); 
        list.printList(head); 
        head = list.reverse(head); 
        System.out.println(""); 
        System.out.println("Reversed linked list "); 
        list.printList(head); 
    } 
}