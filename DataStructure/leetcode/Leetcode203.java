/**
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 *  https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class Leetcode203 {
    static class ListNode {
        int val;
        ListNode next;
     
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

	public static void print(ListNode node) {
		ListNode cur = node;
		while(cur!=null) {
			System.out.print(cur.val);
			System.out.print("-->");

			cur= cur.next;
		}
		System.out.println();
	}

	public static void main(String [] args) {


		ListNode same = new ListNode(0);
		same.next = new ListNode(10);
		same.next.next = new ListNode(-4);
		same.next.next.next = new ListNode(8);

		
		ListNode headB = new ListNode(30);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(10);
		headB.next.next.next = same; 

		print(headB);
	    ListNode nNode = new Leetcode203().removeElements(headB,10);	

		print(nNode);
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode header = new ListNode(-1);
		header.next = head;
		ListNode cur = header;
		while(cur.next != null){
			if(cur.next.val == val ){
				cur.next = cur.next.next;
			}else{
				cur = cur.next;
			}
		}
		return header.next;	
	}
}
