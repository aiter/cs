/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode160 {
    static class ListNode {
        int val;
        ListNode next;
     
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

	public static void main(String [] args) {


		ListNode same = new ListNode(0);
		same.next = new ListNode(10);
		same.next.next = new ListNode(-4);
		same.next.next.next = new ListNode(8);

		ListNode headA = new ListNode(3);
		headA.next = new ListNode(2);
		headA.next.next = same;
		
		ListNode headB = new ListNode(30);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(10);
		headB.next.next.next = same; 

	    ListNode nNode = new Leetcode160().getIntersectionNode(headA, headB);	

		while(nNode !=null) {
			System.out.print(nNode.val);
			System.out.print("-->");

			nNode = nNode.next;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}

		ListNode first = headA;
		ListNode second = headB;

		while(first != second) {
			first = first == null? headB : first.next;
			second = second == null? headA : second.next;
		}

		return first;
	}

}
