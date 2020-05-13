/**
 *
 * Kth Node From End of List LCCI
 *
 * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/ 
 */
public class LeetcodeLastElement {
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


		ListNode same = new ListNode(12);
		same.next = new ListNode(15);
		same.next.next = new ListNode(8);
		same.next.next.next = new ListNode(8);

		
		ListNode headB = new ListNode(15);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(12);
		headB.next.next.next = same; 

		print(headB);
	    int nodeValue = new LeetcodeLastElement().kthToLast(headB, 3);	
		System.out.println(nodeValue);

		for(int i=1;i<=7;i++){
			System.out.println(new LeetcodeLastElement().kthToLast(headB,i));
		}
	}

	public int kthToLast(ListNode head, int k) {
		if(head == null) {
			return -1;
		}
		ListNode fast = head;
		while(k-- > 0) {
			fast = fast.next;
		}
		ListNode slow = head;
		while(fast !=null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow.val;
	}

	/**
	 * 快慢想错误了 :-(
	 */
	public int kthToLast1(ListNode head, int k) {
		if(head == null) {
			return -1;
		}
		ListNode slow = head;
		ListNode fast = head;
		int size = 0;
		while(slow !=null && fast != null) {
			fast = fast.next;
			if(fast !=null) {
				fast = fast.next;
				slow = slow.next;
				size++;
			}
		}
		fast = slow;
		while(slow !=null) {
			slow = slow.next;
			size++;
		}
		int idx = size -k;
		int begin = 0;
		if(idx >= size/2) {
			head = fast;
			begin = size/2;
		}
		for(int i=begin;i<idx;i++){
			head = head.next;
		}
		return head.val;
	}
}
