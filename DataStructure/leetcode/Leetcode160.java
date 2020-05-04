/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 
 * 实际的运用场景是什么？ 主要是思路
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

	/**
	 *  定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
	 *  两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
	 **/
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}

		ListNode first = headA;
		ListNode second = headB;

		// 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
		while(first != second) {
			first = first == null? headB : first.next;
			second = second == null? headA : second.next;
		}

		return first;
	}

}
