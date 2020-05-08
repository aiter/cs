/**
 *
 * Reverse a singly linked list.
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/ 
 */
public class Leetcode206 {
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
	    ListNode nNode = new Leetcode206().reverseList(headB);	

		print(nNode);
	}

	public ListNode reverseList(ListNode head) {
		ListNode pre = null; 
		ListNode cur = head; 
		ListNode next = null; 
		while(cur != null){
			//移动next
			next = cur.next;
			//上一步，保证不会链表丢失
			cur.next = pre;
			//移动pre
			pre = cur;
			//移动cur
			cur = next;
		}
		//注意返回的pre,不是cur,cur会移动到null
		return pre;	
	}
}
