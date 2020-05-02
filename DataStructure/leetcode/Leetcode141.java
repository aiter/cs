/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 
 * Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-cycle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode141 {
    static class ListNode {
        int val;
        ListNode next;
     
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

	public static void main(String [] args) {
		ListNode head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(-4);
		
		head.next.next.next.next = head.next;

	    System.out.println(new Leetcode141().hasCycle(head));	

		ListNode head0 = new ListNode(1);
		head0.next = new ListNode(2);
		head0.next.next = head; 
	    System.out.println(new Leetcode141().hasCycle(head0));	
	}

	/**
	 * 快慢，直接到队尾(next==null)了，就没有环。
	 * 快慢相等了，就是有环
	 */
    public boolean hasCycle(ListNode head) {
		if(head.next == null) {
			return false;
		}
		ListNode slow = head;
 		ListNode fast = head;
		while(fast.next != null) {
			System.out.println(slow.val+":"+fast.val);
			slow = slow.next;
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
			}
			if(fast == null) {
				return false;
			}
			if(slow == fast) {
				return true;
			}
		}		
		return false; 
    }
}
