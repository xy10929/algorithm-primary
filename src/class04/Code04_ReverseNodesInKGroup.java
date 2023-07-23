package class04;

//lc25
public class Code04_ReverseNodesInKGroup {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = head;
		ListNode end = getGroup(start, k);
		if (end == null) {// 一组也凑不齐
			return head;
		}
		reverse(start, end);
		head = end;// 第一组反转后的开头为要返回的结果的开头 所以不需要被其他节点指向
		ListNode lastEnd = start;// 之后每次反转后的结尾都需要记录 以指向下一组反转结果的开头
		while (lastEnd.next != null) {
			start = lastEnd.next;// 找到下一组的start
			end = getGroup(start, k);
			if (end == null) {
				return head;
			}
			reverse(start, end);
			lastEnd.next = end;// 上一组的结尾指向当前组反转后的开头
			lastEnd = start;// 把当前组的结尾更新为lastEnd
		}
		return head;
	}

	public ListNode getGroup(ListNode start, int k) {
		while (start != null && k > 1) {// 从start开始数k个 返回组内最后一个 不够k个则返回null
			start = start.next;
			k--;
		}
		return start;
	}

	// 链表中start到end范围逆序 逆序后的结尾接入原链表
	public void reverse(ListNode start, ListNode end) {
		end = end.next;// 记录 在原链表中 逆序后结尾要连向的位置
		ListNode pre = null;
		ListNode next = null;
		ListNode head = start;
		while (head != end) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		start.next = end;// 逆序后的结尾接入原链表
	}

}