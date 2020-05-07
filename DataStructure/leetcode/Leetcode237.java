/**
 *
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/ 
 */
public class Leetcode237 {
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
		same.next = new ListNode(3);
		same.next.next = new ListNode(-4);
		same.next.next.next = new ListNode(8);

		
		ListNode headB = new ListNode(30);
		headB.next = new ListNode(12);
		headB.next.next = new ListNode(10);
		headB.next.next.next = same; 

		print(headB);
	    new Leetcode237().deleteNode(same);	

		print(headB);
	}

	// node表示的是要删除的结点
    public void deleteNode(ListNode node) {
        // 因为无法访问前一个结点，所以可以把要删除的结点的后一个结点的值前移
        node.val = node.next.val;
        // 然后删除掉后一个结点
        node.next = node.next.next;
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
