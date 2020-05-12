import java.util.BitSet;
/**
 *
 * Remove duplicate node 
 *
 * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
 */
public class LeetcodeRemoveDuplicateNode {
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
	    ListNode newNode = new LeetcodeRemoveDuplicateNode().removeDuplicateNodes(headB);	
		print(newNode);

	}

	public ListNode removeDuplicateNodes(ListNode head) {
		if(head == null) {
			return null;
		}
		// 15-->12-->12-->12-->15-->8-->8-->
		BitSet bitSet = new BitSet(2000);
		ListNode first = head;
		bitSet.set(first.val);
		while(first!=null && first.next!=null) {

			while(first.next!=null && bitSet.get(first.next.val)) {
				first.next = first.next.next;
			}
			if(first.next !=null) {
				bitSet.set(first.next.val);
			}
			first = first.next;
		}
		return head;
	}

	public ListNode removeDuplicateNodes1(ListNode head) {
		if(head == null) {
			return null;
		}
		// 不让用临时缓存，那么就多次循环
		ListNode cur = head;
		while(cur!=null) {
			ListNode first = cur;
			while(first.next!=null) {
				if(first.next.val == cur.val) {
					first.next = first.next.next;
				}else {
					first = first.next;
				}
			}
			cur = cur.next;
		}
		return head;
	}
}
