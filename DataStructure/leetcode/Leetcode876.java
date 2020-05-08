/**
 *
 * Middle of the Linked List
 *
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class Leetcode876 {
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
		same.next.next = new ListNode(4);
		same.next.next.next = new ListNode(8);

		
		ListNode headB = new ListNode(-1);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(10);
		headB.next.next.next = same; 

		print(headB);
	    ListNode nNode = new Leetcode876().middleNode(headB);	

		print(nNode);
	}

	public ListNode middleNode(ListNode head) {
		ListNode slow = head; 
		ListNode fast = head; 
		while(fast != null){
			fast = fast.next;
			if(fast == null) {
				return slow;
			}
			fast = fast.next;
			slow = slow.next;
		}
		return slow;	
	}
}
