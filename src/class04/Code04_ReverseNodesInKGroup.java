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
		ListNode end = getGroupEnd(start, k);
		if (end == null) {// 一组也凑不齐
			return head;
		}
		head = end;// 第一组逆序后的头为整个链最后的头 不变
		reverse(start, end);// 完成组内反转 但start和end指向的节点不变
		ListNode lastEnd = start;// 上一组的结尾节点即start
		while (lastEnd.next != null) {
			start = lastEnd.next;// 找到下一组的start
			end = getGroupEnd(start, k);
			if (end == null) {
				return head;
			}
			reverse(start, end);
			lastEnd.next = end;// 反转后end为当前组的开头 被上一组的结尾指向
			lastEnd = start;// start反转后为当前组的结尾 也即此后的上一组的结尾
		}
		return head;
	}

	public static ListNode getGroupEnd(ListNode start, int k) {// start开始数k个 返回组内最后一个 不够k个会返回null
		while (--k != 0 && start != null) {
			start = start.next;
		}
		return start;
	}

	public static void reverse(ListNode start, ListNode end) {// start到end逆序
		end = end.next;// 记录逆序后要连向的位置
		ListNode pre = null;
		ListNode next = null;
		ListNode head = start;
		while (head != end) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		start.next = end;// 原头为逆序后的尾 连向记录好的位置
	}

}