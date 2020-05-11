/**
 *
 * Convert Binary Number in a Linked List to Integer 
 *
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class Leetcode1290 {
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
		same.next = new ListNode(1);
		same.next.next = new ListNode(1);
		same.next.next.next = new ListNode(0);

		
		ListNode headB = new ListNode(1);
		headB.next = new ListNode(1);
		headB.next.next = new ListNode(0);
		headB.next.next.next = same; 

		print(headB);
	    int num = new Leetcode1290().getDecimalValue(headB);	
		System.out.println(num);

	}

    public int getDecimalValue(ListNode head) {
		int num = 0;
		// 主要是使用位移动，每个node移动一位，并把新的node放在最末位的一位上
		while(head!=null) {
			num = num << 1 | head.val;
			head = head.next;
		}
		return num;
    }
}
