/**
 *
 * Given a singly linked list, determine if it is a palindrome.
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/ 
 */
public class Leetcode234 {
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

		
		ListNode headB = new ListNode(15);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(120);
		headB.next.next.next = same; 

		print(headB);
	    boolean isPalindrome = new Leetcode234().isPalindrome(headB);	
		System.out.println(isPalindrome);

		ListNode headc = new ListNode(15);
		headc.next = new ListNode(12);
		headc.next.next = new ListNode(12);
		headc.next.next.next = new ListNode(15);
		print(headc);
	    System.out.println(new Leetcode234().isPalindrome(headc));	
	}

	public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
		ListNode first = head;
		ListNode slow = head;
		ListNode fast = head;
		//找中间节点
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		//反转后半部分
	    ListNode pre = null;
	    ListNode cur = slow;
		ListNode next = null;	
		while(cur !=null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		//比较？
		while(first.val == pre.val) {
			first = first.next;
			pre = pre.next;
			if(pre == null) {
				return true;
			}
		}

		return false;
	}
}
